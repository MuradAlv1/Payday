package com.example.accountservice.dto.order;

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
    private BigDecimal amountInUsd;
    private Long userId;
}
