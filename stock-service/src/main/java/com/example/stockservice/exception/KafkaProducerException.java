package com.example.stockservice.exception;

public class KafkaProducerException extends RuntimeException {
    public KafkaProducerException(String message) {
        super(message);
    }
}
