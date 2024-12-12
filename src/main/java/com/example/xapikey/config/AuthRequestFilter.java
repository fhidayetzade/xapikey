package com.example.xapikey.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AuthRequestFilter extends OncePerRequestFilter {

    private final List<AuthService> authServices;

    public AuthRequestFilter(@Lazy List<AuthService> authServices) {
        this.authServices = authServices;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) {
        try {
            Optional<Authentication> authOptional = Optional.empty();

            for (AuthService authService : authServices) {
                authOptional = authOptional.or(() -> authService.getAuthentication(request));
                if (authOptional.isPresent()) {
                    break;
                }
            }
            authOptional.ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new IllegalArgumentException("exception");
        }
    }
}