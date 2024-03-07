package com.example.orderservice.service;

import static com.example.orderservice.constant.OrderStatus.PENDING;

import com.example.orderservice.dto.StockResponseDto;
import com.example.orderservice.dto.order.OrderRequestDto;
import com.example.orderservice.dto.order.OrderResponseDto;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.persistence.entity.Order;
import com.example.orderservice.persistence.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto create(Order order) {
        // TODO: validate stockID
        order.setId(UUID.randomUUID());
        order.setStatus(PENDING);
        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto create(OrderRequestDto source) {
        return create(orderMapper.toEntity(source));
    }

    @Override
    @Transactional
    public List<Order> getMatchedOrders(StockResponseDto source) {
        val result = orderRepository.findMatchedOrders(source.getId(), source.getPrice());
        log.info("Found %s matched orders".formatted(result.size()));
        return result;
    }
}
