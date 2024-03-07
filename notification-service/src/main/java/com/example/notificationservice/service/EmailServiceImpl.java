package com.example.notificationservice.service;

import com.example.notificationservice.dto.EmailRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Override
    public void send(final EmailRequestDto emailRequestDto) {
        log.info(
                "Email(content = %s) sent to %s"
                        .formatted(emailRequestDto.getMessage(), emailRequestDto.getToEmail()));
    }
}
