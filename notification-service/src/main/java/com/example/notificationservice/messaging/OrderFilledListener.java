package com.example.notificationservice.messaging;

import com.example.notificationservice.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFilledListener {

    private final EmailService emailService;

    @KafkaListener(groupId = "order-filled-topic-consumer", topics = "order-filled")
    public void receive(String message) {
        // TODO: send email to user
    }
}
