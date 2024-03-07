package com.example.orderexecutor.persistence.entity;

import static jakarta.persistence.EnumType.STRING;

import com.example.orderexecutor.constant.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

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
    private UUID stockId;

    @Enumerated(STRING)
    private OrderStatus status;

    private BigDecimal targetPriceInUsd;
    private BigDecimal amount;
    @CreationTimestamp private Instant createdAt;
}
