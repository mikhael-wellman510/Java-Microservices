package com.example.account_service.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommonResponse <T>{

    private Integer statusCode;
    private String message;
    private T data;

    @Override
    public String toString() {
        return "CommonResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
