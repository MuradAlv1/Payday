package com.example.stockservice.mapper;

import com.example.stockservice.dto.StockResponseDto;
import com.example.stockservice.entity.Stock;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock toEntity(StockResponseDto source);

    StockResponseDto toResponseDto(Stock stock);

    List<StockResponseDto> toResponseDtos(List<Stock> stocks);

    List<Stock> toEntities(List<StockResponseDto> stocks);
}
