package com.example.stockservice.controller;

import com.example.stockservice.dto.StockPageResponseDto;
import com.example.stockservice.service.StockService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping
    public StockPageResponseDto get(
            @RequestParam(defaultValue = "0", required = false) final Integer page,
            @RequestParam(defaultValue = "10", required = false) final Integer size) {
        return stockService.get(page, size);
    }
}
