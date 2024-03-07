package com.example.orderexecutor.service;

import static com.example.orderexecutor.constant.OrderStatus.FAILED;
import static com.example.orderexecutor.constant.OrderStatus.PENDING;
import static com.example.orderexecutor.constant.OrderStatus.SUCCEEDED;

import com.example.orderexecutor.dto.order.OrderFailedDto;
import com.example.orderexecutor.dto.order.OrderFilledDto;
import com.example.orderexecutor.dto.reserve.AmountReserveDto;
import com.example.orderexecutor.messaging.publisher.order.OrderFailedEventPublisher;
import com.example.orderexecutor.messaging.publisher.order.OrderFilledEventPublisher;
import com.example.orderexecutor.persistence.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderRepository orderRepository;
    private final OrderFilledEventPublisher orderFilledEventPublisher;
    private final OrderFailedEventPublisher orderFailedEventPublisher;

    @Transactional
    public void orderSucceeded(AmountReserveDto amountReserveDto) {
        val orderId = amountReserveDto.getOrderId();
        orderRepository.updateStatusById(orderId, SUCCEEDED);
        orderFilledEventPublisher.publish(
                OrderFilledDto.builder()
                        .amountInUsd(amountReserveDto.getAmountInUsd())
                        .price(amountReserveDto.getPrice())
                        .stockId(amountReserveDto.getStockId())
                        .userId(amountReserveDto.getUserId())
                        .orderId(orderId)
                        .build());
    }

    @Transactional
    public void orderFailed(AmountReserveDto amountReserveDto) {
        val orderId = amountReserveDto.getOrderId();
        orderRepository.updateStatusById(orderId, FAILED);
        orderFailedEventPublisher.publish(
                OrderFailedDto.builder()
                        .userId(amountReserveDto.getUserId())
                        .orderId(orderId)
                        .build());
    }

    @Transactional
    public void setPendingStatus(AmountReserveDto amountReserveDto) {
        val orderId = amountReserveDto.getOrderId();
        orderRepository.updateStatusById(orderId, PENDING);
    }
}
