package com.example.accountservice.messaging.publisher.user;

import com.example.accountservice.configuration.kafka.MessagingConfigData;
import com.example.accountservice.dto.UserCreatedMessage;
import com.example.accountservice.messaging.KafkaProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreatedEventPublisherImpl implements UserCreatedEventPublisher {

    private final KafkaProducer<String, UserCreatedMessage> kafkaProducer;
    private final MessagingConfigData configData;

    @Override
    public void publish(final UserCreatedMessage message) {
        kafkaProducer.send(configData.getUsersTopicName(), message);
    }
}
