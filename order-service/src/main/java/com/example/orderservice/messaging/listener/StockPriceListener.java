package com.example.orderservice.messaging.listener;

import static com.example.orderservice.constant.OrderStatus.SENT_TO_EXECUTOR;

import com.example.orderservice.dto.StockResponseDto;
import com.example.orderservice.messaging.publisher.order.OrderMatchedEventPublisher;
import com.example.orderservice.persistence.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
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
public class StockPriceListener {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderMatchedEventPublisher orderMatchedEventPublisher;

    @SneakyThrows
    @KafkaListener(
            groupId = "${order-service.stocks-topic-consumer-group}",
            topics = "${order-service.stocks-topic-name}")
    public void receive(String message) {
        val stockResponseDto = objectMapper.readValue(message, StockResponseDto.class);
        log.info("Received: %s".formatted(stockResponseDto.toString()));
        val matchedOrders = orderService.getMatchedOrders(stockResponseDto);
        for (var order : matchedOrders) {
            order.setStatus(SENT_TO_EXECUTOR);
            orderRepository.save(order);
            orderMatchedEventPublisher.publish(order);
        }
    }
}
