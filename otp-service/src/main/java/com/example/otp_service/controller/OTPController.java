package com.example.otp_service.controller;

import com.example.otp_service.dto.CommonResponse;
import com.example.otp_service.dto.OtpResponse;
import com.example.otp_service.dto.RegisterCheckDto;
import com.example.otp_service.dto.RegisterVerificationDto;
import com.example.otp_service.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class OTPController {

    private final OtpService otpService;
    private final Environment environment;

    @PostMapping("/request")
    public ResponseEntity<?>requestOTP(@RequestBody RegisterCheckDto registerCheckDto){
        log.debug("Request OTP {}" , registerCheckDto);

        OtpResponse otpResponse = otpService.requestOTP(registerCheckDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<OtpResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Succes")
                        .data(otpResponse)
                        .build()
                );

    }

    @GetMapping("/test-loadbalancer")
    public String testLoadBalancer (){
        String port = environment.getProperty("local.server.port");

        log.debug("Port : {} ", port );
        return "Ok with port : " + port;
    }

    @PostMapping("/verification")
    public ResponseEntity<?>verificationOTP(@RequestBody RegisterVerificationDto registerVerificationDto){

        String result = otpService.verificationOtp(registerVerificationDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message("Sukses")
                        .data(result)
                        .build()
                );
    }


}
