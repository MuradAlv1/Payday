package com.example.orderservice.persistence.repository;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

import com.example.orderservice.persistence.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Lock(PESSIMISTIC_WRITE)
    @Query(
            "select o from Order o "
                    + "where o.status = 'PENDING' "
                    + "and o.stockId = :stockId "
                    + "and o.targetPriceInUsd >= :currentPrice")
    List<Order> findMatchedOrders(UUID stockId, BigDecimal currentPrice);
}
