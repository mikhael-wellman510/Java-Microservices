package com.example.account_service.feignClient;

import com.example.account_service.dto.CommonResponse;
import com.example.account_service.dto.OtpResponse;
import com.example.account_service.dto.RegisterCheckDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "otp-service") // Ini harus mengikuti nama yg ada di eureka atau nama service nya
public interface OTPClient {

    @PostMapping("/request")
    public ResponseEntity<CommonResponse<OtpResponse>> requestOTP(@RequestBody RegisterCheckDto registerCheckDto);

    @GetMapping("/test-loadbalancer")
    public String testLoadBalancer();
}
