package com.example.orderservice.messaging.publisher.order;

import com.example.orderservice.configuration.kafka.MessagingConfigData;
import com.example.orderservice.dto.order.OrderResponseDto;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.messaging.publisher.KafkaProducer;
import com.example.orderservice.persistence.entity.Order;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMatchedEventPublisherImpl implements OrderMatchedEventPublisher {

    private final KafkaProducer<String, OrderResponseDto> kafkaProducer;
    private final MessagingConfigData orderMessagingConfigData;
    private final OrderMapper orderMapper;

    @Override
    public void publish(Order order) {
        kafkaProducer.send(
                orderMessagingConfigData.getOrdersMatchedTopicName(),
                orderMapper.toResponseDto(order));
    }
}
