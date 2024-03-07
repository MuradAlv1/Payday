package com.example.accountservice.persistence.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "usd_balances")
public class UsdBalance {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull @OneToOne private User user;
    @NotNull private BigDecimal availableAmount;
    @NotNull private BigDecimal reservedAmount;
}
