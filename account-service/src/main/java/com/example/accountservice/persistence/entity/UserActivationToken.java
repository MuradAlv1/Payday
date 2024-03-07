package com.example.accountservice.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@Setter
@RedisHash(value = "UserActivationToken", timeToLive = 3600 * 24)
public class UserActivationToken {
    @Id private Long userId;
    private String token;
}
