package com.example.stockservice.messaging.publisher;

import com.example.stockservice.dto.StockResponseDto;

public interface StockPriceEventPublisher {
    void publish(StockResponseDto stockResponseDto);
}
