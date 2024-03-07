package com.example.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderFilledDto {

    private UUID orderId;
    private UUID stockId;
    private BigDecimal price;
    private Long userId;
    private BigDecimal amountInUsd;
}
