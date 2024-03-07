package com.example.accountservice.messaging.publisher.reserve;

import com.example.accountservice.configuration.kafka.MessagingConfigData;
import com.example.accountservice.dto.reserve.AmountReserveDto;
import com.example.accountservice.messaging.KafkaProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AmountReserveEventPublisherImpl implements AmountReserveEventPublisher {

    private final KafkaProducer<String, AmountReserveDto> kafkaProducer;
    private final MessagingConfigData configData;

    @Override
    public void publish(AmountReserveDto message) {
        kafkaProducer.send(configData.getAmountReserveTopicName(), message);
    }
}
