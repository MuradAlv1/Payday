package com.example.accountservice.configuration;

import static com.example.accountservice.constant.Errors.Jwt.JwtExpiredError.JWT_EXPIRED_MESSAGE;
import static com.example.accountservice.constant.Errors.Jwt.JwtInvalidError.JWT_INVALID_MESSAGE;

import com.example.accountservice.service.auth.jwt.JwtParser;
import com.example.accountservice.service.error.ErrorResponseHandler;
import com.example.accountservice.util.SecurityUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtParser jwtProvider;
    private final ErrorResponseHandler responseHandler;

    @Override
    public void doFilterInternal(
            final HttpServletRequest httpRequest,
            final HttpServletResponse servletResponse,
            final FilterChain filterChain)
            throws ServletException, IOException {

        try {
            val token = SecurityUtil.extractToken(httpRequest);
            val authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(httpRequest, servletResponse);
        } catch (Exception e) {
            if (e instanceof JwtException) {
                if (e instanceof ExpiredJwtException) {
                    responseHandler.setResponse(servletResponse, JWT_EXPIRED_MESSAGE.getMessage());
                    return;
                }
                responseHandler.setResponse(servletResponse, JWT_INVALID_MESSAGE.getMessage());
                return;
            }
            filterChain.doFilter(httpRequest, servletResponse);
        }
    }
}
