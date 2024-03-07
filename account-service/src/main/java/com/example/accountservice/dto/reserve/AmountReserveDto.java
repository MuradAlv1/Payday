package com.example.accountservice.dto.reserve;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AmountReserveDto {
    private Long userId;
    private UUID orderId;
    private UUID stockId;
    private BigDecimal price;
    private BigDecimal amountInUsd;
    private AmountReserveStatus status;

    public enum AmountReserveStatus {
        SUCCEEDED,
        FAILED
    }
}
