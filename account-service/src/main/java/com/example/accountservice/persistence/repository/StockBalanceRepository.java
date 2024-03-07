package com.example.accountservice.persistence.repository;

import com.example.accountservice.persistence.entity.StockBalance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockBalanceRepository extends JpaRepository<StockBalance, Long> {

    Boolean existsByUserIdAndStockId(Long userId, UUID stockId);

    Optional<StockBalance> findByUserIdAndStockId(Long userId, UUID stockId);
}
