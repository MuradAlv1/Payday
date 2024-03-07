package com.example.accountservice.dto.user;

import com.example.accountservice.constant.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private UserStatus status;
}
