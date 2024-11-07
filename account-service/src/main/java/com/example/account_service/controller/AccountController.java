package com.example.account_service.controller;

import com.example.account_service.Service.AccountService;
import com.example.account_service.dto.CommonResponse;
import com.example.account_service.dto.RegisterCheckDto;
import com.example.account_service.dto.RegisterCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/check")
    public ResponseEntity<?> registerCheck(@RequestBody RegisterCheckDto registerCheckDto){
        log.debug("Register {}" , registerCheckDto);

        RegisterCheckResponse registerCheckResponse = accountService.registerCheck(registerCheckDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<RegisterCheckResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Succes")
                        .data(registerCheckResponse)
                        .build()
                );
    }

    @GetMapping("/load")
    public String testLoadBalancer(){

        return accountService.testLoadBalancer();
    };

}
