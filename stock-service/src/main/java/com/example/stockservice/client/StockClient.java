package com.example.stockservice.client;

import com.example.stockservice.dto.StockResponseDto;

import java.util.List;

/** Third-Party client */
public interface StockClient {

    List<StockResponseDto> getAll();
}
