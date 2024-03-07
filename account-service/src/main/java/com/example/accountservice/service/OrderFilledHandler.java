package com.example.accountservice.service;

import com.example.accountservice.dto.ReceiveStockDto;
import com.example.accountservice.dto.order.OrderFilledDto;
import com.example.accountservice.service.account.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFilledHandler {

    private final AccountService accountService;

    @Transactional
    public void handle(final OrderFilledDto orderFilledDto) {
        log.info("Spending reserved balance..");
        accountService.spendReservedBalance(
                orderFilledDto.getUserId(), orderFilledDto.getAmountInUsd());

        val stockAmount = orderFilledDto.getAmountInUsd().divide(orderFilledDto.getPrice());
        log.info(String.valueOf(stockAmount) + "amount received");
        accountService.receiveStock(
                ReceiveStockDto.builder()
                        .userId(orderFilledDto.getUserId())
                        .amount(stockAmount)
                        .stockId(orderFilledDto.getStockId())
                        .build());
    }
}
