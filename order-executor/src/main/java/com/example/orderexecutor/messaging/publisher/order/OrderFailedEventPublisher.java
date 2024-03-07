package com.example.orderexecutor.messaging.publisher.order;

import com.example.orderexecutor.dto.order.OrderFailedDto;

public interface OrderFailedEventPublisher {

    void publish(OrderFailedDto message);
}
