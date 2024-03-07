package com.example.notificationservice.util;

import com.example.notificationservice.dto.OrderFilledDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserEmailBuilder {

    @Value("${clients.account-service}")
    private String accountServiceUrl;

    public String buildActivationUrl(String activationToken, Long userId) {
        return "%s?token=%s&userId=%s".formatted(accountServiceUrl, activationToken, userId);
    }

    public String buildOrderFilledMessage(final OrderFilledDto orderFilledDto) {
        return "Your order Id = %s filled !".formatted(orderFilledDto.getOrderId());
    }
}
