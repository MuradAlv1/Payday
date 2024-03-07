package com.example.accountservice.messaging.listener;

import com.example.accountservice.dto.order.OrderFilledDto;
import com.example.accountservice.service.OrderFilledHandler;
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
public class OrderFilledListener {

    private final ObjectMapper objectMapper;
    private final OrderFilledHandler orderFilledHandler;

    @KafkaListener(
            groupId = "${account-service.order-filled-topic-consumer-group}",
            topics = "${account-service.orders-filled-topic-name}")
    public void receive(String message) {
        try {
            val orderFilledDto = objectMapper.readValue(message, OrderFilledDto.class);
            log.info("Received: %s".formatted(orderFilledDto.toString()));
            orderFilledHandler.handle(orderFilledDto);
        } catch (JsonProcessingException e) {
            log.error("Invalid json: %s".formatted(e.getMessage()));
        }
    }
}
