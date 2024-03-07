package com.example.accountservice.service.auth.jwt;

import static com.example.accountservice.util.SecurityUtil.extractToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;

import lombok.val;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtParserImpl implements JwtParser {

    @Value("${security.jwt.secretKey}")
    private String secretKey;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public Authentication getAuthentication(String token) {
        val claims = parseClaims(token);
        val email = claims.get("id", String.class);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        UserDetails userDetails = new User(email, "", authorityList);

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorityList);
    }

    @Override
    public Long getUserId(String token) {
        return Long.parseLong((String) (parseClaims(token).get("id")));
    }

    @Override
    public Long getUserIdFromBearer(String bearer) {
        return getUserId(extractToken(bearer));
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
