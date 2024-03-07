package com.example.orderservice.persistence.entity;

import static jakarta.persistence.EnumType.STRING;

import com.example.orderservice.constant.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "orders",
        indexes = {@Index(columnList = "status")})
public class Order {

    @Id private UUID id;

    @NotNull private UUID stockId;
    @NotNull private Long userId;

    @NotNull
    @Enumerated(STRING)
    private OrderStatus status;

    @NotNull private BigDecimal targetPriceInUsd;
    @NotNull private BigDecimal amount;
    @CreationTimestamp private Instant createdAt;
}
