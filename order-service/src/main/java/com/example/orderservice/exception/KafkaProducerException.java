package com.example.orderservice.exception;

public class KafkaProducerException extends RuntimeException {
    public KafkaProducerException(String message) {
        super(message);
    }
}
