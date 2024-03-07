package com.example.orderexecutor.service;

import com.example.orderexecutor.dto.order.OrderResponseDto;

public interface OrderExecutionService {

    void process(OrderResponseDto orderMessage);
}
