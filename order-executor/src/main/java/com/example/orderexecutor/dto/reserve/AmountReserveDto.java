package com.example.orderexecutor.dto.reserve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AmountReserveDto {
    private Long userId;
    private UUID orderId;
    private BigDecimal amountInUsd;
    private AmountReserveStatus status;
    private UUID stockId;
    private BigDecimal price;

    public enum AmountReserveStatus {
        SUCCEEDED,
        FAILED
    }
}
