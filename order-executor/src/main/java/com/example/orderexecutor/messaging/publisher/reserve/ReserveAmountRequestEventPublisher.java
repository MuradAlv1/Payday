package com.example.orderexecutor.messaging.publisher.reserve;

import com.example.orderexecutor.dto.reserve.ReserveAmountRequest;

public interface ReserveAmountRequestEventPublisher {

    void publish(ReserveAmountRequest message);
}
