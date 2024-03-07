package com.example.orderservice.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    @Schema(hidden = true)
    private Long userId;

    @NotNull private UUID stockId;
    @NotNull private BigDecimal targetPriceInUsd;
    @NotNull private BigDecimal amount;
}
