package com.example.accountservice.service.account;

import static com.example.accountservice.constant.Errors.buildNotFoundMessage;
import static com.example.accountservice.constant.UserStatus.ACTIVE;
import static com.example.accountservice.constant.UserStatus.INACTIVE;

import static java.lang.Boolean.FALSE;
import static java.math.BigDecimal.ZERO;

import com.example.accountservice.dto.DepositDto;
import com.example.accountservice.dto.ReceiveStockDto;
import com.example.accountservice.dto.UsdBalanceResponseDto;
import com.example.accountservice.dto.reserve.ReserveBalanceResponseDto;
import com.example.accountservice.exception.ApplicationException;
import com.example.accountservice.exception.NotFoundException;
import com.example.accountservice.mapper.UsdBalanceMapper;
import com.example.accountservice.persistence.entity.StockBalance;
import com.example.accountservice.persistence.entity.UsdBalance;
import com.example.accountservice.persistence.repository.StockBalanceRepository;
import com.example.accountservice.persistence.repository.UsdBalanceRepository;
import com.example.accountservice.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final UsdBalanceRepository usdBalanceRepository;
    private final UsdBalanceMapper usdBalanceMapper;
    private final StockBalanceRepository stockBalanceRepository;

    @Override
    @Transactional
    public void activateAccount(Long userId) {
        userRepository.updateStatus(userId, ACTIVE);
        if (FALSE == usdBalanceRepository.existsByUserId(userId)) {
            val usdBalance = new UsdBalance();
            usdBalance.setReservedAmount(ZERO);
            usdBalance.setAvailableAmount(ZERO);
            usdBalance.setUser(userRepository.getReferenceById(userId));
            usdBalanceRepository.save(usdBalance);
        }
    }

    @Override
    @Transactional
    public UsdBalanceResponseDto deposit(final DepositDto depositDto) {
        val user =
                userRepository
                        .findByIdWithLock(depositDto.getUserId())
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                buildNotFoundMessage(
                                                        "user",
                                                        "id",
                                                        String.valueOf(depositDto.getUserId()))));
        if (user.getStatus() == INACTIVE) {
            throw new ApplicationException("Account must be active!");
        }

        usdBalanceRepository.updateBalance(depositDto.getAmountInUsd(), depositDto.getUserId());
        val usdBalance =
                usdBalanceRepository
                        .findByUserId(depositDto.getUserId())
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                buildNotFoundMessage(
                                                        "balance",
                                                        "userId",
                                                        String.valueOf(depositDto.getUserId()))));

        return usdBalanceMapper.toResponseDto(usdBalance);
    }

    @Override
    @Transactional
    public ReserveBalanceResponseDto reserveAmount(Long userId, BigDecimal amountInUsd) {
        val usdBalance = getUsdBalance(userId);
        if (usdBalance.getAvailableAmount().compareTo(amountInUsd) < 0) {
            return new ReserveBalanceResponseDto(false);
        }
        usdBalance.setAvailableAmount(usdBalance.getAvailableAmount().subtract(amountInUsd));
        usdBalance.setReservedAmount(usdBalance.getReservedAmount().add(amountInUsd));
        usdBalanceRepository.save(usdBalance);

        return new ReserveBalanceResponseDto(true);
    }

    @Override
    @Transactional
    public void spendReservedBalance(Long userId, BigDecimal amountInUsd) {
        val usdBalance = getUsdBalance(userId);
        if (usdBalance.getReservedAmount().compareTo(amountInUsd) < 0) {
            log.error("Insufficient reserved amount");
            throw new ApplicationException("Unable to spend specified amount");
        }
        usdBalance.setReservedAmount(usdBalance.getReservedAmount().subtract(amountInUsd));
        usdBalanceRepository.save(usdBalance);
    }

    @Override
    @Transactional
    public void receiveStock(final ReceiveStockDto source) {
        val userId = source.getUserId();
        val stockId = source.getStockId();
        if (stockBalanceRepository.existsByUserIdAndStockId(userId, stockId)) {
            val stockBalance = stockBalanceRepository.findByUserIdAndStockId(userId, stockId).get();
            stockBalance.setAvailableAmount(
                    stockBalance.getAvailableAmount().add(source.getAmount()));
            stockBalanceRepository.save(stockBalance);
        } else {
            val stockBalance = new StockBalance();
            stockBalance.setUser(userRepository.getReferenceById(userId));
            stockBalance.setStockId(stockId);
            stockBalance.setAvailableAmount(ZERO);
            stockBalance.setReservedAmount(ZERO);
            stockBalanceRepository.save(stockBalance);
        }
        log.info("User %s received %s stock".formatted(userId, stockId));
    }

    private UsdBalance getUsdBalance(Long userId) {
        return usdBalanceRepository
                .findByUserIdWithLock(userId)
                .orElseThrow(
                        () ->
                                new NotFoundException(
                                        buildNotFoundMessage(
                                                "balance", "userId", String.valueOf(userId))));
    }
}
