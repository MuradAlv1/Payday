package com.example.accountservice.service.auth;

import com.example.accountservice.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsService
        implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        val user = userService.getByEmail(pin);
        Set<SimpleGrantedAuthority> roles = new HashSet<>();

        return new User(user.getEmail(), user.getPassword(), roles);
    }
}
