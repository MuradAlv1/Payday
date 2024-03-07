package com.example.accountservice.service;

import static com.example.accountservice.dto.reserve.AmountReserveDto.AmountReserveStatus.FAILED;
import static com.example.accountservice.dto.reserve.AmountReserveDto.AmountReserveStatus.SUCCEEDED;

import com.example.accountservice.dto.reserve.AmountReserveDto;
import com.example.accountservice.dto.reserve.ReserveAmountRequest;
import com.example.accountservice.messaging.publisher.reserve.AmountReserveEventPublisher;
import com.example.accountservice.service.account.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReserveAmountRequestHandler {

    private final AccountService accountService;
    private final AmountReserveEventPublisher amountReserveEventPublisher;

    @Transactional
    public void process(ReserveAmountRequest reserveAmountRequest) {
        val result =
                accountService.reserveAmount(
                        reserveAmountRequest.getUserId(), reserveAmountRequest.getAmountInUsd());

        val event =
                AmountReserveDto.builder()
                        .orderId(reserveAmountRequest.getOrderId())
                        .stockId(reserveAmountRequest.getStockId())
                        .price(reserveAmountRequest.getPrice())
                        .userId(reserveAmountRequest.getUserId())
                        .amountInUsd(reserveAmountRequest.getAmountInUsd())
                        .build();

        if (result.getReserved()) {
            event.setStatus(SUCCEEDED);
        } else {
            event.setStatus(FAILED);
        }
        amountReserveEventPublisher.publish(event);
    }
}
