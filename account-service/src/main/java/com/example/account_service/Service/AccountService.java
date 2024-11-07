package com.example.account_service.Service;

import com.example.account_service.dto.RegisterCheckDto;
import com.example.account_service.dto.RegisterCheckResponse;
import com.example.account_service.dto.RegisterVerificationDto;

public interface AccountService {

    RegisterCheckResponse registerCheck(RegisterCheckDto registerCheckDto);
    String testLoadBalancer();

    String verification(RegisterVerificationDto registerVerificationDto);
}
