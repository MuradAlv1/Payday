package com.example.orderexecutor.exception;

public class KafkaProducerException extends RuntimeException {
    public KafkaProducerException(String message) {
        super(message);
    }
}
