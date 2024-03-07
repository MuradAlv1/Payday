package com.example.accountservice.service.user;

import static com.example.accountservice.constant.Errors.buildAlreadyExistsMessage;
import static com.example.accountservice.constant.Errors.buildNotFoundMessage;
import static com.example.accountservice.constant.UserStatus.INACTIVE;

import com.example.accountservice.dto.UserCreatedMessage;
import com.example.accountservice.dto.user.UserCreateDto;
import com.example.accountservice.dto.user.UserResponseDto;
import com.example.accountservice.exception.AlreadyExistsException;
import com.example.accountservice.exception.NotFoundException;
import com.example.accountservice.mapper.UserMapper;
import com.example.accountservice.messaging.publisher.user.UserCreatedEventPublisher;
import com.example.accountservice.persistence.entity.User;
import com.example.accountservice.persistence.entity.UserActivationToken;
import com.example.accountservice.persistence.repository.UserActivationTokenRepository;
import com.example.accountservice.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserActivationTokenRepository activationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserCreatedEventPublisher userCreatedEventPublisher;

    private User create(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException(
                    buildAlreadyExistsMessage("User", "email", user.getEmail()));
        }
        user.setStatus(INACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserResponseDto create(final UserCreateDto source) {
        if (existsByEmail(source.getEmail())) {
            throw new AlreadyExistsException(
                    buildAlreadyExistsMessage("User", "email", source.getEmail()));
        }
        val user = create(userMapper.toEntity(source));
        val activationToken = String.valueOf(UUID.randomUUID());
        activationTokenRepository.save(
                UserActivationToken.builder().userId(user.getId()).token(activationToken).build());
        userCreatedEventPublisher.publish(
                UserCreatedMessage.builder()
                        .email(user.getEmail())
                        .userId(user.getId())
                        .activationToken(activationToken)
                        .build());

        return userMapper.toResponseDto(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () ->
                                new NotFoundException(
                                        buildNotFoundMessage(
                                                "User", "email", String.valueOf(email))));
    }

    @Override
    public User getById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new NotFoundException(
                                        buildNotFoundMessage("User", "id", String.valueOf(id))));
    }

    private Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
