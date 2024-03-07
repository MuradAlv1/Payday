package com.example.accountservice.messaging.publisher.reserve;

import com.example.accountservice.dto.reserve.AmountReserveDto;

public interface AmountReserveEventPublisher {
    void publish(AmountReserveDto message);
}
