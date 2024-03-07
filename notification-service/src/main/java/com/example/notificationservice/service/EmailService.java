package com.example.notificationservice.service;

import com.example.notificationservice.dto.EmailRequestDto;

public interface EmailService {

    void send(EmailRequestDto emailRequestDto);
}
