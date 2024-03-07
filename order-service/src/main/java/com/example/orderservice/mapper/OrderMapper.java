package com.example.orderservice.mapper;

import com.example.orderservice.dto.order.OrderRequestDto;
import com.example.orderservice.dto.order.OrderResponseDto;
import com.example.orderservice.persistence.entity.Order;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDto toResponseDto(Order source);

    Order toEntity(OrderRequestDto source);
}
