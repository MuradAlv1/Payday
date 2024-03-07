package com.example.accountservice.persistence.repository;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

import com.example.accountservice.persistence.entity.UsdBalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface UsdBalanceRepository extends JpaRepository<UsdBalance, Long> {

    Boolean existsByUserId(Long userId);

    @Modifying
    @Query(
            "update UsdBalance u set u.availableAmount = u.availableAmount + :amount "
                    + "where u.user.id = :userId")
    void updateBalance(BigDecimal amount, Long userId);

    Optional<UsdBalance> findByUserId(Long userId);

    @Lock(PESSIMISTIC_WRITE)
    @Query("select u from UsdBalance u where u.user.id = :userId ")
    Optional<UsdBalance> findByUserIdWithLock(Long userId);
}
