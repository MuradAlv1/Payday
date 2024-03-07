package com.example.accountservice.messaging.publisher.user;

import com.example.accountservice.dto.UserCreatedMessage;

public interface UserCreatedEventPublisher {
    void publish(UserCreatedMessage message);
}
