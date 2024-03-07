package com.example.orderservice.client;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.example.orderservice.dto.UserResponseDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "account-service", url = "${clients.account-service}")
public interface AccountClient {

    @PostMapping("/api/v1/auth/validate")
    UserResponseDto getUser(@RequestHeader(AUTHORIZATION) String bearer);
}
