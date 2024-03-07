package com.example.accountservice.service.account;

import com.example.accountservice.dto.DepositDto;
import com.example.accountservice.dto.ReceiveStockDto;
import com.example.accountservice.dto.UsdBalanceResponseDto;
import com.example.accountservice.dto.reserve.ReserveBalanceResponseDto;

import java.math.BigDecimal;

public interface AccountService {

    void activateAccount(Long userId);

    UsdBalanceResponseDto deposit(DepositDto depositDto);

    ReserveBalanceResponseDto reserveAmount(Long userId, BigDecimal amountInUsd);

    void spendReservedBalance(Long userId, BigDecimal amountInUsd);

    void receiveStock(ReceiveStockDto source);
}
