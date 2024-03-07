package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockResponseDto {
    private UUID id;
    private String symbol;
    private BigDecimal price;
}
