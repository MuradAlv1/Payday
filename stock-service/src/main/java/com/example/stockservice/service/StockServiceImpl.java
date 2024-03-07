package com.example.stockservice.service;

import com.example.stockservice.dto.StockPageResponseDto;
import com.example.stockservice.mapper.StockMapper;
import com.example.stockservice.repository.StockRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Override
    public StockPageResponseDto get(final Integer page, final Integer size) {
        val result = stockRepository.findAllPaginated(PageRequest.of(page, size));
        return StockPageResponseDto.builder()
                .totalSize(result.getTotalElements())
                .stocks(stockMapper.toResponseDtos(result.getContent()))
                .build();
    }
}
