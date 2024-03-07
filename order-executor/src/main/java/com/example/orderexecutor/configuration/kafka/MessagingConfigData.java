package com.example.orderexecutor.configuration.kafka;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "order-executor-service")
public class MessagingConfigData {
    private String ordersFilledTopicName;
    private String ordersMatchedTopicName;
    private String ordersFailedTopicName;
    private String reserveAmountRequestTopicName;
    private String amountReserveTopicName;
    private String ordersMatchedTopicConsumerGroup;
    private String amountReserveTopicConsumerGroup;
}
