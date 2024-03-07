package com.example.accountservice.persistence.repository;

import com.example.accountservice.persistence.entity.UserActivationToken;

import org.springframework.data.repository.CrudRepository;

public interface UserActivationTokenRepository extends CrudRepository<UserActivationToken, Long> {}
