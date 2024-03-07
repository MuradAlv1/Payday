package com.example.notificationservice.configuration.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicsConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    public static final String USERS_TOPIC = "users";

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic users() {
        return new NewTopic(USERS_TOPIC, 1, (short) 1);
    }
}
