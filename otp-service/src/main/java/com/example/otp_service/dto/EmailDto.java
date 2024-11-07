package com.example.otp_service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EmailDto {
    private String to;
    private String subject;
    private String body;
}
