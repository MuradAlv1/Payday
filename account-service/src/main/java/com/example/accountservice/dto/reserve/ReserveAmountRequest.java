package com.example.accountservice.dto.reserve;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ReserveAmountRequest {
    private Long userId;
    private UUID orderId;
    private UUID stockId;
    private BigDecimal price;
    private BigDecimal amountInUsd;
}
