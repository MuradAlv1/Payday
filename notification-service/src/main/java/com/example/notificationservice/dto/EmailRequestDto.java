package com.example.notificationservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailRequestDto {
    private String toEmail;
    private String message;
}
