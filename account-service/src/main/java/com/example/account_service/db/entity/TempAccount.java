package com.example.account_service.db.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@RedisHash(value = "account" , timeToLive = 3600)
public class TempAccount {
    @Id
    private String id;
    private String email;
    private boolean valid = false;

    @Override
    public String toString() {
        return "TempAccount{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", valid=" + valid +
                '}';
    }
}
