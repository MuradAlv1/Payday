package com.example.orderexecutor.messaging.publisher.order;

import com.example.orderexecutor.configuration.kafka.MessagingConfigData;
import com.example.orderexecutor.dto.order.OrderFailedDto;
import com.example.orderexecutor.messaging.KafkaProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFailedEventPublisherImpl implements OrderFailedEventPublisher {

    private final KafkaProducer<String, OrderFailedDto> kafkaProducer;
    private final MessagingConfigData configData;

    @Override
    public void publish(final OrderFailedDto message) {
        kafkaProducer.send(configData.getOrdersFailedTopicName(), message);
    }
}
