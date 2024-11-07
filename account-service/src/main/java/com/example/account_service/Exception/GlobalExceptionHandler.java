package com.example.account_service.Exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<String> handleFeignClientException(FeignException.FeignClientException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.status());

        String errorMessage = ex.getMessage();

        System.out.println("Error: " + errorMessage);

        // Return response dengan status dan pesan error
        return ResponseEntity.status(status).body("Error in Feign Client: " + errorMessage);
    }


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict occurred: " + ex.getMessage());
    }
}
