package com.example.otp_service.service;

import com.example.otp_service.dto.OtpResponse;
import com.example.otp_service.dto.RegisterCheckDto;

public interface OtpService {
    OtpResponse requestOTP(RegisterCheckDto registerCheckDto);
}
