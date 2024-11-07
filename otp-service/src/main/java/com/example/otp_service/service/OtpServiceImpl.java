package com.example.otp_service.service;

import com.example.otp_service.db.entity.TempOTP;
import com.example.otp_service.db.repositori.TempOTPRepositori;
import com.example.otp_service.dto.EmailDto;
import com.example.otp_service.dto.OtpResponse;
import com.example.otp_service.dto.RegisterCheckDto;
import com.example.otp_service.dto.RegisterVerificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class OtpServiceImpl implements OtpService{

    private final TempOTPRepositori tempOTPRepositori;
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

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

        // Send message broker

        sendEmail(email,"Kode verifikasi anda : " +randomOtp);


        return OtpResponse.builder()
                .email(email)
                .otp(randomOtp)
                .build();
    }

    @Override
    public String verificationOtp(RegisterVerificationDto registerVerificationDto) {
        // Check By Email
        TempOTP tempOTPByEmail =tempOTPRepositori.getFirstByEmail(registerVerificationDto.getEmail());

        System.out.println("Njir lahhh " +tempOTPByEmail);
        if (tempOTPByEmail == null){
            throw new NoSuchElementException("tidak ada data");
        }

        // Verification / validasi OTP
        if (!tempOTPByEmail.getOtp().equals(registerVerificationDto.getOtp())){
            throw new NoSuchElementException("Otp Tidak valid");
        }


        System.out.println("anja bisa : "  + tempOTPByEmail);

        return "Succes";
    }

    private void sendEmail(String to , String body){
        EmailDto emailDto = new EmailDto();
        emailDto.setTo(to);
        emailDto.setSubject("Kode Verifikasi");
        emailDto.setBody(body);
        redisTemplate.convertAndSend(channelTopic.getTopic(),emailDto);
    }

    private String generateOtp(){

        return new DecimalFormat("0000").format(new Random().nextInt(9999));
    }
}
