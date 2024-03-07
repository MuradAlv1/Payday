package com.example.accountservice.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.example.accountservice.dto.user.UserCreateDto;
import com.example.accountservice.dto.user.UserResponseDto;
import com.example.accountservice.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "UserController")
public class UserController {

    private final UserService userService;

    @Operation(description = "Register via name, email and password")
    @PostMapping
    @ResponseStatus(CREATED)
    public UserResponseDto create(@RequestBody @Valid final UserCreateDto source) {
        return userService.create(source);
    }
}
