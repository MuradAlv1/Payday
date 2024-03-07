package com.example.accountservice.mapper;

import com.example.accountservice.dto.UsdBalanceResponseDto;
import com.example.accountservice.persistence.entity.UsdBalance;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsdBalanceMapper {

    UsdBalanceResponseDto toResponseDto(UsdBalance source);
}
