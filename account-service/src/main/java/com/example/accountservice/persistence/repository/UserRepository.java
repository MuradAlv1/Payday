package com.example.accountservice.persistence.repository;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

import com.example.accountservice.constant.UserStatus;
import com.example.accountservice.persistence.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Modifying
    @Query("update User u set u.status = :status where u.id = :userId")
    Integer updateStatus(Long userId, UserStatus status);

    @Lock(PESSIMISTIC_WRITE)
    @Query("select u from User u where u.id = :id")
    Optional<User> findByIdWithLock(Long id);
}
