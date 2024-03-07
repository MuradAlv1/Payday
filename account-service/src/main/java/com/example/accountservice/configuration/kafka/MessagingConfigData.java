package com.example.accountservice.configuration.kafka;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "account-service")
public class MessagingConfigData {
    private String reserveAmountRequestTopicName;
    private String amountReserveTopicName;
    private String ordersFilledTopicName;
    private String usersTopicName;
    private String orderFilledTopicConsumerGroup;
    private String reserveAmountRequestTopicConsumerGroup;
}
