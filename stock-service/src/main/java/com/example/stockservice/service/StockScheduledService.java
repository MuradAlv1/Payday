package com.example.stockservice.service;

import com.example.stockservice.client.StockClient;
import com.example.stockservice.dto.StockResponseDto;
import com.example.stockservice.messaging.publisher.StockPriceEventPublisher;
import com.example.stockservice.repository.StockRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Order(2)
@Service
@RequiredArgsConstructor
public class StockScheduledService {

    private final StockClient stockClient;
    private final StockRepository stockRepository;
    private final StockPriceEventPublisher stockPriceEventPublisher;

    @Scheduled(fixedRate = 5000L)
    @Transactional
    public void updateStocks() {
        log.info("Updating stocks prices ...");
        List<StockResponseDto> stocks = stockClient.getAll();
        for (var stock : stocks) {
            try {
                stockRepository.updatePrice(stock.getId(), stock.getPrice());
                stockPriceEventPublisher.publish(stock);
            } catch (Exception e) {
                log.error(String.valueOf(e));
            }
        }
    }
}
