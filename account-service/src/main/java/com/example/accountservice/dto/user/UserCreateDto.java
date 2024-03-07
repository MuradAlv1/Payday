package com.example.accountservice.dto.user;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

@Data
public class UserCreateDto {
    @NotBlank private String name;
    @NotBlank private String email;

    @Length(min = 6)
    private String password;
}
