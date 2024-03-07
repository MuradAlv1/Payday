package com.example.stockservice.repository;

import com.example.stockservice.entity.Stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {

    @Modifying
    @Query("update Stock s set s.price = :price where s.id = :id")
    void updatePrice(UUID id, BigDecimal price);

    @Query("select s from Stock s")
    Page<Stock> findAllPaginated(Pageable pageable);
}
