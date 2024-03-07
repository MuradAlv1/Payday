package com.example.orderexecutor.dto.order;

import com.example.orderexecutor.constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private UUID id;
    private UUID stockId;
    private Long userId;
    private OrderStatus status;
    private BigDecimal targetPriceInUsd;
    private BigDecimal amount;
    private Instant createdAt;
}
