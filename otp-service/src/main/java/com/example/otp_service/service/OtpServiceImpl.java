package com.example.otp_service.service;

import com.example.otp_service.db.entity.TempOTP;
import com.example.otp_service.db.repositori.TempOTPRepositori;
import com.example.otp_service.dto.OtpResponse;
import com.example.otp_service.dto.RegisterCheckDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class OtpServiceImpl implements OtpService{

    private final TempOTPRepositori tempOTPRepositori;

    @Override
    public OtpResponse requestOTP(RegisterCheckDto registerCheckDto) {
        String email =  registerCheckDto.getEmail();

        TempOTP tempOTP = tempOTPRepositori.getFirstByEmail(email);
        // Todo -> cek , jika email ada , hapus dan buat otp baru
        System.out.println(tempOTP);
        if (tempOTP != null){
            System.out.println("Ditemukan , Maka hapus data nya agar di generate lagi");
            tempOTPRepositori.delete(tempOTP);
        }


        // Todo -> Generate random otp
        String randomOtp = generateOtp();
        log.debug("random otp: {} " ,randomOtp);

        // Todo -> save ke redis
        TempOTP saveTempOtp = TempOTP.builder()
                .email(email)
                .otp(randomOtp)
                .build();
        tempOTPRepositori.save(saveTempOtp);

        return OtpResponse.builder()
                .email(email)
                .otp(randomOtp)
                .build();
    }

    private String generateOtp(){

        return new DecimalFormat("0000").format(new Random().nextInt(9999));
    }
}
