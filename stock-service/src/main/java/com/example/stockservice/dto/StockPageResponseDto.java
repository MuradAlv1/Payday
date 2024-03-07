package com.example.stockservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class StockPageResponseDto {

    private List<StockResponseDto> stocks;
    private Long totalSize;
}
