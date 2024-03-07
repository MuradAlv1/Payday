package com.example.orderexecutor.messaging.publisher.order;

import com.example.orderexecutor.dto.order.OrderFilledDto;

public interface OrderFilledEventPublisher {

    void publish(OrderFilledDto message);
}
