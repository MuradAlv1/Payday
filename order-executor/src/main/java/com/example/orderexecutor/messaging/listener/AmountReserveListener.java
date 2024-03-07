package com.example.orderexecutor.messaging.listener;

import com.example.orderexecutor.client.ExchangeClient;
import com.example.orderexecutor.dto.reserve.AmountReserveDto;
import com.example.orderexecutor.service.OrderHandler;
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
public class AmountReserveListener {

    private final ObjectMapper objectMapper;
    private final ExchangeClient exchangeClient;
    private final OrderHandler orderHandler;

    @SneakyThrows
    @KafkaListener(
            groupId = "${order-executor-service.amount-reserve-topic-consumer-group}",
            topics = "${order-executor-service.amount-reserve-topic-name}")
    public void receive(String message) {
        val amountReserveDto = objectMapper.readValue(message, AmountReserveDto.class);
        log.info("Received: %s".formatted(amountReserveDto.toString()));
        switch (amountReserveDto.getStatus()) {
            case SUCCEEDED -> {
                // TODO: use outbox for consistency
                exchangeClient.buy(); // assuming that this is idempotent
                orderHandler.orderSucceeded(amountReserveDto);
            }
            case FAILED -> orderHandler.orderFailed(amountReserveDto);
        }
    }
}
