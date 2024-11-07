package com.example.account_service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterCheckResponse {

    private String id;
    private String otp;

    @Override
    public String toString() {
        return "RegisterCheckResponse{" +
                "id='" + id + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
