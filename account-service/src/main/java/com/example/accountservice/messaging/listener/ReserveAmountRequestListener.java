package com.example.accountservice.messaging.listener;

import com.example.accountservice.dto.reserve.ReserveAmountRequest;
import com.example.accountservice.service.ReserveAmountRequestHandler;
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
public class ReserveAmountRequestListener {

    private final ObjectMapper objectMapper;
    private final ReserveAmountRequestHandler reserveAmountRequestHandler;

    @SneakyThrows
    @KafkaListener(
            groupId = "${account-service.reserve-amount-request-topic-consumer-group}",
            topics = "${account-service.reserve-amount-request-topic-name}")
    public void receive(String message) {
        val reserveAmountRequest = objectMapper.readValue(message, ReserveAmountRequest.class);
        log.info("Received: %s".formatted(reserveAmountRequest.toString()));
        reserveAmountRequestHandler.process(reserveAmountRequest);
    }
}
