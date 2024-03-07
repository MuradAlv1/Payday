package com.example.stockservice;

import com.example.stockservice.client.StockClient;
import com.example.stockservice.mapper.StockMapper;
import com.example.stockservice.repository.StockRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupListener {

    private final StockClient stockClient;
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @EventListener
    public void startup(ApplicationStartedEvent event) {
        fillStocks();
    }

    private void fillStocks() {
        var entities = stockMapper.toEntities(stockClient.getAll());
        try {
            stockRepository.saveAll(entities);
        } catch (Exception e) {
            log.info("Skipping initialization..");
        }
    }
}
