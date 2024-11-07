package com.example.account_service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OtpResponse {

    private String email;
    private String otp;

    @Override
    public String toString() {
        return "OtpResponse{" +
                "email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
