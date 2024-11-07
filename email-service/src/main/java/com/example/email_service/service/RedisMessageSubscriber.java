package com.example.email_service.service;

import com.example.email_service.dto.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageListener {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = message.toString();

        try{
            EmailDto emailDto = objectMapper.readValue(msg ,EmailDto.class);
            emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(),emailDto.getBody());
        }catch (JsonProcessingException e){
            e.printStackTrace();

        }

    }
}
