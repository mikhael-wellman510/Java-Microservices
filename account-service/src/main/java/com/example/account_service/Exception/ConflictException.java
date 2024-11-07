package com.example.account_service.Exception;

public class ConflictException extends RuntimeException{

    public ConflictException(String message){
        super(message);
    }
}
