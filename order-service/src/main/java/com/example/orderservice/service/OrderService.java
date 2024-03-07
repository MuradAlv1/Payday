package com.example.orderservice.service;

import com.example.orderservice.dto.StockResponseDto;
import com.example.orderservice.dto.order.OrderRequestDto;
import com.example.orderservice.dto.order.OrderResponseDto;
import com.example.orderservice.persistence.entity.Order;

import java.util.List;

public interface OrderService {

    OrderResponseDto create(Order order);

    OrderResponseDto create(OrderRequestDto source);

    List<Order> getMatchedOrders(StockResponseDto source);
}
