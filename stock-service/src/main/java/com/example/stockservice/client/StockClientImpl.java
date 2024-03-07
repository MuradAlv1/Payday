package com.example.stockservice.client;

import static java.math.BigDecimal.TEN;

import com.example.stockservice.dto.StockResponseDto;

import jakarta.annotation.PostConstruct;

import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/** Mock stock client */
@Component
@Order(1)
public class StockClientImpl implements StockClient {

    private final List<StockResponseDto> stocks = new ArrayList<>();
    private final Random random = new Random();

    @PostConstruct
    private void init() {
        stocks.add(
                StockResponseDto.builder()
                        .id(UUID.fromString("e510dd17-8c13-47f9-8d14-46e2b401b2fe"))
                        .symbol("GOOGL")
                        .price(TEN)
                        .build());

        stocks.add(
                StockResponseDto.builder()
                        .id(UUID.fromString("e410dd17-8c13-47f9-8d14-46e2b401b2fe"))
                        .symbol("TSLA")
                        .price(TEN)
                        .build());

        stocks.add(
                StockResponseDto.builder()
                        .id(UUID.fromString("7f040fb7-c9dc-4be4-88ea-13085d7168db"))
                        .symbol("AMZN")
                        .price(TEN)
                        .build());
    }

    @Override
    public List<StockResponseDto> getAll() {
        return stocks;
    }

    @Scheduled(fixedRate = 5000L)
    public void updatePrices() {
        stocks.forEach(stack -> stack.setPrice(BigDecimal.valueOf(random.nextInt(4, 10))));
    }
}
