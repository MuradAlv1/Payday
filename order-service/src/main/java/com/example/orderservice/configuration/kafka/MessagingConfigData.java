package com.example.orderservice.configuration.kafka;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "order-service")
public class MessagingConfigData {
    private String ordersTopicName;
    private String ordersMatchedTopicName;
    private String stocksTopicName;
    private String stocksTopicConsumerGroup;
}
