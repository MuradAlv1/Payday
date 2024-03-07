package com.example.accountservice.service.user;

import com.example.accountservice.persistence.entity.UserActivationToken;
import com.example.accountservice.persistence.repository.UserActivationTokenRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActivationTokenService {

    private final UserActivationTokenRepository activationTokenRepository;

    public UserActivationToken getByUserId(Long userId) {
        return activationTokenRepository.findById(userId).orElseThrow(); // TODO: throw not found ex
    }
}
