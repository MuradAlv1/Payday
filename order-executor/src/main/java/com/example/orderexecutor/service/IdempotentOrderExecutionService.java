package com.example.orderexecutor.service;

import static com.example.orderexecutor.constant.OrderStatus.PROCESSING;
import static com.example.orderexecutor.constant.OrderStatus.SENT_TO_EXECUTOR;

import com.example.orderexecutor.dto.order.OrderResponseDto;
import com.example.orderexecutor.dto.reserve.ReserveAmountRequest;
import com.example.orderexecutor.messaging.publisher.reserve.ReserveAmountRequestEventPublisher;
import com.example.orderexecutor.persistence.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdempotentOrderExecutionService implements OrderExecutionService {

    private final OrderRepository orderRepository;
    private final ReserveAmountRequestEventPublisher reserveAmountRequestEventPublisher;

    @Override
    @Transactional
    public void process(final OrderResponseDto orderMessage) {
        log.info("Received: %s".formatted(orderMessage.toString()));
        int updateCount =
                orderRepository.updateOrderStatusIfCurrentStatus(
                        orderMessage.getId(), SENT_TO_EXECUTOR, PROCESSING);

        if (updateCount == 0) {
            log.info(
                    "Order {} is already being processed or is not in a state to be processed.",
                    orderMessage.getId());
            return;
        }

        reserveAmountRequestEventPublisher.publish(
                ReserveAmountRequest.builder()
                        .amountInUsd(orderMessage.getAmount())
                        .orderId(orderMessage.getId())
                        .price(orderMessage.getTargetPriceInUsd())
                        .stockId(orderMessage.getStockId())
                        .userId(orderMessage.getUserId())
                        .build());
    }
}
