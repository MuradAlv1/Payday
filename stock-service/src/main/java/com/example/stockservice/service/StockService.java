package com.example.stockservice.service;

import com.example.stockservice.dto.StockPageResponseDto;

public interface StockService {

    StockPageResponseDto get(Integer page, Integer size);
}
