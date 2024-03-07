package com.example.orderexecutor.messaging.listener;

import com.example.orderexecutor.dto.order.OrderResponseDto;
import com.example.orderexecutor.service.OrderExecutionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMatchedListener {

    private final ObjectMapper objectMapper;
    private final OrderExecutionService orderExecutionService;

    @SneakyThrows
    @KafkaListener(
            groupId = "${order-executor-service.orders-matched-topic-consumer-group}",
            topics = "${order-executor-service.orders-matched-topic-name}")
    public void receive(String message) {
        val orderMessage = objectMapper.readValue(message, OrderResponseDto.class);
        log.info("Received: %s".formatted(orderMessage.toString()));
        orderExecutionService.process(orderMessage);
    }
}
