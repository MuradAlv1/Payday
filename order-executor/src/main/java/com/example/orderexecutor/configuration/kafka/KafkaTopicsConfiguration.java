package com.example.orderexecutor.configuration.kafka;

import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicsConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    private final MessagingConfigData configData;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic matchedOrders() {
        return new NewTopic(configData.getOrdersMatchedTopicName(), 1, (short) 1);
    }

    @Bean
    public NewTopic reserveAmountRequests() {
        return new NewTopic(configData.getReserveAmountRequestTopicName(), 1, (short) 1);
    }

    @Bean
    public NewTopic orderFilled() {
        return new NewTopic(configData.getOrdersFilledTopicName(), 1, (short) 1);
    }

    @Bean
    public NewTopic orderFailed() {
        return new NewTopic(configData.getOrdersFailedTopicName(), 1, (short) 1);
    }
}
