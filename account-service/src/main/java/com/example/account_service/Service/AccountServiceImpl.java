package com.example.account_service.Service;

import com.example.account_service.Exception.ConflictException;
import com.example.account_service.db.entity.Account;
import com.example.account_service.db.entity.TempAccount;
import com.example.account_service.dto.*;
import com.example.account_service.feignClient.OTPClient;
import com.example.account_service.repositori.AccountRepository;
import com.example.account_service.repositori.TempAccountRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final TempAccountRepository tempAccountRepository;
    private final OTPClient otpClient;

    @Override
    public RegisterCheckResponse registerCheck(RegisterCheckDto registerCheckDto) {

        String email = registerCheckDto.getEmail();

        // Check data di postgres
       Account accountByEmail = accountRepository.getFirstByEmail(email);
        if (accountByEmail != null){
            throw new ConflictException("Conflict bos !");
        }

        TempAccount tempAccountByEmail =tempAccountRepository.getFirstByEmail(email);
        if (tempAccountByEmail != null){
            throw new ConflictException("Conflict bos !");
        }

        TempAccount tempAccount = TempAccount.builder()
                .id(UUID.randomUUID().toString())
                .email(registerCheckDto.getEmail())
                .valid(false)
                .build();

        // Todo -> Save ke redis
      TempAccount save =  tempAccountRepository.save(tempAccount);



        try{
            //Todo -> Request OTP -> PANGGIL SERVICE OTP
            otpClient.requestOTP(registerCheckDto);
        }catch (FeignException ex){
            throw new  RuntimeException(ex);
        }

        ResponseEntity<CommonResponse<OtpResponse>> responseEntity = otpClient.requestOTP(registerCheckDto);
        System.out.println( "hasil nya : " + responseEntity);

        OtpResponse otpResponse = OtpResponse.builder()
                .otp(responseEntity.getBody().getData().getOtp())
                .email(responseEntity.getBody().getData().getEmail())
                .build();

        System.out.println("Cek response " +  otpResponse);
        return RegisterCheckResponse.builder()
                .id(save.getId())
                .build();
    }

    @Override
    public String verification(RegisterVerificationDto registerVerificationDto) {

        //todo -> Check Redis
        TempAccount tempAccount = tempAccountRepository.getFirstByEmail(registerVerificationDto.getEmail());


        System.out.println("Cek hasil " + tempAccount);
        if (tempAccount==null) {
            throw new NoSuchElementException("Data tidak ada");
        }

        //todo -> Verification Otp

        try{
          otpClient.verificationOTP(registerVerificationDto);

        }catch (FeignException.FeignClientException ex){
            ex.printStackTrace();
            throw new NoSuchElementException("Otp Tidak valid");
        }
        // Updated verification
        tempAccount.setValid(true);
        tempAccountRepository.save(tempAccount);



        return "Valid Bro";
    }

    @Override
    public String testLoadBalancer() {

        return otpClient.testLoadBalancer();
    }
}
