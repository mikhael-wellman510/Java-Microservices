package com.example.email_service.service;

public interface EmailService {
    void sendEmail(String to , String subject, String body);
}
