package com.example.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositDto {
    @Schema(hidden = true)
    private Long userId;

    private BigDecimal amountInUsd;
}
