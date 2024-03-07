package com.example.accountservice.service.user;

import com.example.accountservice.dto.user.UserCreateDto;
import com.example.accountservice.dto.user.UserResponseDto;
import com.example.accountservice.persistence.entity.User;

public interface UserService {

    UserResponseDto create(UserCreateDto source);

    User getByEmail(String email);

    User getById(Long id);
}
