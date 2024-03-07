package com.example.notificationservice.messaging;

import static com.example.notificationservice.configuration.kafka.KafkaTopicsConfiguration.USERS_TOPIC;

import com.example.notificationservice.dto.EmailRequestDto;
import com.example.notificationservice.dto.UserCreatedMessage;
import com.example.notificationservice.service.EmailService;
import com.example.notificationservice.util.UserEmailBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreatedListener {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    private final UserEmailBuilder userEmailBuilder;

    @KafkaListener(groupId = "users-topic-consumer", topics = USERS_TOPIC)
    public void receive(String message) {
        try {
            val userCreatedMessage = objectMapper.readValue(message, UserCreatedMessage.class);
            log.info("Received: %s".formatted(userCreatedMessage.toString()));
            emailService.send(
                    EmailRequestDto.builder()
                            .toEmail(userCreatedMessage.getEmail())
                            .message(
                                    userEmailBuilder.buildActivationUrl(
                                            userCreatedMessage.getActivationToken(),
                                            userCreatedMessage.getUserId()))
                            .build());
        } catch (JsonProcessingException e) {
            log.error(String.valueOf(e));
        }
    }
}
