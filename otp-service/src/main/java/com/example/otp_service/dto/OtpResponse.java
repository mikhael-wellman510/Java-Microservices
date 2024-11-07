package com.example.otp_service.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OtpResponse {
    private String email;
    private String otp;
}
