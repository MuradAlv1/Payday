package com.example.accountservice.persistence.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(
        name = "stock_balances",
        indexes = {@Index(columnList = "user_id"), @Index(columnList = "stock_id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"stockId, user"})})
public class StockBalance {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull private UUID stockId;
    @ManyToOne @NotNull private User user;
    @NotNull private BigDecimal availableAmount;
    @NotNull private BigDecimal reservedAmount;
}
