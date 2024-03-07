package com.example.orderexecutor.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilledDto {

    private UUID orderId;
    private UUID stockId;
    private BigDecimal price;
    private Long userId;
    private BigDecimal amountInUsd;
}
