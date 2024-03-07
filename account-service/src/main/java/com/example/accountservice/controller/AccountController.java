package com.example.accountservice.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import static java.lang.Boolean.FALSE;

import com.example.accountservice.dto.DepositDto;
import com.example.accountservice.dto.UsdBalanceResponseDto;
import com.example.accountservice.exception.ForbiddenException;
import com.example.accountservice.service.account.AccountService;
import com.example.accountservice.service.auth.jwt.JwtParser;
import com.example.accountservice.service.user.UserActivationTokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
@Tag(name = "AccountController")
public class AccountController {

    private final AccountService accountService;
    private final UserActivationTokenService userActivationTokenService;
    private final JwtParser jwtParser;

    @Operation(description = "Activate account")
    @GetMapping
    public void activateAccount(@RequestParam final Long userId, @RequestParam final String token) {
        if (FALSE == userActivationTokenService.getByUserId(userId).getToken().equals(token)) {
            throw new ForbiddenException("Invalid activation token");
        }
        accountService.activateAccount(userId);
    }

    @Operation(
            description = "Deposit money to account",
            security = @SecurityRequirement(name = "Bearer Authentication"))
    @PostMapping("/deposit")
    public UsdBalanceResponseDto deposit(
            @RequestBody final DepositDto source,
            @RequestHeader(value = AUTHORIZATION, required = false) final String bearer) {
        val userId = jwtParser.getUserIdFromBearer(bearer);
        source.setUserId(userId);

        return accountService.deposit(source);
    }
}
