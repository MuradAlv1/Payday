package com.example.accountservice.exception;

public class AlreadyExistsException extends ApplicationException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
