package com.example.otp_service.service;

import com.example.otp_service.dto.OtpResponse;
import com.example.otp_service.dto.RegisterCheckDto;
import com.example.otp_service.dto.RegisterVerificationDto;

public interface OtpService {
    OtpResponse requestOTP(RegisterCheckDto registerCheckDto);
    String verificationOtp(RegisterVerificationDto registerVerificationDto);
}
