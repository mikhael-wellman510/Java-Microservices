package com.example.account_service.Service;

import com.example.account_service.dto.RegisterCheckDto;
import com.example.account_service.dto.RegisterCheckResponse;

public interface AccountService {

    RegisterCheckResponse registerCheck(RegisterCheckDto registerCheckDto);
    String testLoadBalancer();
}
