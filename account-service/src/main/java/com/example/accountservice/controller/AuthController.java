package com.example.accountservice.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.example.accountservice.dto.TokenDto;
import com.example.accountservice.dto.user.UserLoginDto;
import com.example.accountservice.dto.user.UserResponseDto;
import com.example.accountservice.mapper.UserMapper;
import com.example.accountservice.service.auth.AuthService;
import com.example.accountservice.service.auth.jwt.JwtParser;
import com.example.accountservice.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Tag(name = "AuthController")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final JwtParser jwtParser;
    private final UserMapper userMapper;

    @Operation(description = "Login via email and password")
    @PostMapping("/login")
    public TokenDto login(@RequestBody @Valid final UserLoginDto source) {
        return authService.login(source);
    }

    @PostMapping("/validate")
    public UserResponseDto getById(@RequestHeader(AUTHORIZATION) final String bearer) {
        val userId = jwtParser.getUserIdFromBearer(bearer);
        val user = userService.getById(userId);

        return userMapper.toResponseDto(user);
    }
}
