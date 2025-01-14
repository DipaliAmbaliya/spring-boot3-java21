package com.example.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@TestConfiguration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {
    @Bean
    public SecurityWebFilterChain webSpringSecurityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(auth -> {
            auth.anyExchange().permitAll();
        }).csrf(csrfSpec -> csrfSpec.disable()).build();
    }
}