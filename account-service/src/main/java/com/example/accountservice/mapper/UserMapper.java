package com.example.accountservice.mapper;

import com.example.accountservice.dto.user.UserCreateDto;
import com.example.accountservice.dto.user.UserResponseDto;
import com.example.accountservice.persistence.entity.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateDto source);

    UserResponseDto toResponseDto(User user);
}
