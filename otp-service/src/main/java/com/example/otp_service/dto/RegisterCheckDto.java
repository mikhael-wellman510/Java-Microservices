package com.example.otp_service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterCheckDto {
    private String email;

    @Override
    public String toString() {
        return "RegisterCheckDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
