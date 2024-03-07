package com.example.orderservice.messaging.publisher.order;

import com.example.orderservice.persistence.entity.Order;

public interface OrderMatchedEventPublisher {

    void publish(Order order);
}
