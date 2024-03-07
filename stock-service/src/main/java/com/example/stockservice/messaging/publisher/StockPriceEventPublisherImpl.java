package com.example.stockservice.messaging.publisher;

import com.example.stockservice.configuration.kafka.StocksMessagingConfigData;
import com.example.stockservice.dto.StockResponseDto;
import com.example.stockservice.messaging.KafkaProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceEventPublisherImpl implements StockPriceEventPublisher {

    private final KafkaProducer<String, StockResponseDto> kafkaProducer;
    private final StocksMessagingConfigData stocksProducerConfigData;

    @Override
    public void publish(final StockResponseDto stockResponseDto) {
        kafkaProducer.send(stocksProducerConfigData.getStocksTopicName(), stockResponseDto);
    }
}
