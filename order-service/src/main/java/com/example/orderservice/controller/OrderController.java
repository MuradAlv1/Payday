package com.example.orderservice.controller;

import static com.example.orderservice.constant.UserStatus.INACTIVE;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;

import com.example.orderservice.client.AccountClient;
import com.example.orderservice.dto.order.OrderRequestDto;
import com.example.orderservice.dto.order.OrderResponseDto;
import com.example.orderservice.exception.ApplicationException;
import com.example.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
@Tag(name = "OrderController")
public class OrderController {

    private final OrderService orderService;
    private final AccountClient accountClient;

    @Operation(
            description = "Place limit order",
            security = @SecurityRequirement(name = "Bearer Authentication"))
    @PostMapping
    @ResponseStatus(CREATED)
    public OrderResponseDto create(
            @RequestBody @Valid final OrderRequestDto source,
            @RequestHeader(value = AUTHORIZATION, required = false) final String bearer) {
        val user = accountClient.getUser(bearer);
        if (user.getStatus().equals(INACTIVE)) {
            throw new ApplicationException("Account is not active");
        }

        source.setUserId(user.getId());

        return orderService.create(source);
    }
}
