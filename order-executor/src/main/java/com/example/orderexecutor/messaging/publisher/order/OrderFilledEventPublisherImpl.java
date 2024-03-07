package com.example.orderexecutor.messaging.publisher.order;

import com.example.orderexecutor.configuration.kafka.MessagingConfigData;
import com.example.orderexecutor.dto.order.OrderFilledDto;
import com.example.orderexecutor.messaging.KafkaProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFilledEventPublisherImpl implements OrderFilledEventPublisher {

    private final KafkaProducer<String, OrderFilledDto> kafkaProducer;
    private final MessagingConfigData configData;

    @Override
    public void publish(final OrderFilledDto message) {
        kafkaProducer.send(configData.getOrdersFilledTopicName(), message);
    }
}
