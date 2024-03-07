package com.example.accountservice.service.auth.jwt;

import org.springframework.security.core.Authentication;

public interface JwtProvider {

    String generateToken(Authentication authentication);
}
