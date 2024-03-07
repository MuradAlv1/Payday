package com.example.orderexecutor.messaging.publisher.reserve;

import com.example.orderexecutor.configuration.kafka.MessagingConfigData;
import com.example.orderexecutor.dto.reserve.ReserveAmountRequest;
import com.example.orderexecutor.messaging.KafkaProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReserveAmountRequestEventPublisherImpl implements ReserveAmountRequestEventPublisher {

    private final KafkaProducer<String, ReserveAmountRequest> kafkaProducer;
    private final MessagingConfigData configData;

    @Override
    public void publish(final ReserveAmountRequest message) {
        kafkaProducer.send(configData.getReserveAmountRequestTopicName(), message);
    }
}
