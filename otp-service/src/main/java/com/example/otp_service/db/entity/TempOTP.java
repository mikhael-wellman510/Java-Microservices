package com.example.otp_service.db.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@RedisHash(value = "otp" , timeToLive = 300)
public class TempOTP {

    @Id
    private String id;
    private String otp;
    @Indexed
    private String email;

    @Override
    public String toString() {
        return "TempOTP{" +
                "id='" + id + '\'' +
                ", otp='" + otp + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
