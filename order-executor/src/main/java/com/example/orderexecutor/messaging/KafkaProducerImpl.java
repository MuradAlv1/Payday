package com.example.orderexecutor.messaging;

import com.example.orderexecutor.exception.KafkaProducerException;

import jakarta.annotation.PreDestroy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImpl<K extends Serializable, V> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public void send(final String topicName, final V message) {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            kafkaTemplate.send(topicName, message);
        } catch (KafkaException e) {
            log.error(
                    "Error on kafka producer with message: {} and exception: {}",
                    message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with message: " + message);
        }
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}
