package com.example.orderexecutor.persistence.repository;

import com.example.orderexecutor.constant.OrderStatus;
import com.example.orderexecutor.persistence.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Modifying
    @Query(
            "UPDATE Order o SET o.status = :newStatus WHERE o.id = :orderId AND o.status = :currentStatus")
    Integer updateOrderStatusIfCurrentStatus(
            UUID orderId, OrderStatus currentStatus, OrderStatus newStatus);

    @Modifying
    @Query("update Order o set o.status = :status where o.id = :id")
    Integer updateStatusById(UUID id, OrderStatus status);
}
