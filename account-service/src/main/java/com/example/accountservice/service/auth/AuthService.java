package com.example.accountservice.service.auth;

import com.example.accountservice.dto.TokenDto;
import com.example.accountservice.dto.user.UserLoginDto;

public interface AuthService {

    TokenDto login(UserLoginDto userLoginDto);
}
