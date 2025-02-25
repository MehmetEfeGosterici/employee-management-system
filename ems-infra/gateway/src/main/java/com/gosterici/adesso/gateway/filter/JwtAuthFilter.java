package com.gosterici.adesso.gateway.filter;

import com.gosterici.adesso.gateway.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
public class JwtAuthFilter implements WebFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        String path = exchange.getRequest().getURI().getPath();
        if (!path.startsWith("/auth/")) {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);
                try {
                    if (jwtService.isTokenValid(jwt)) {
                        String username = jwtService.extractUsername(jwt);
                        List<String> authorities = jwtService.extractAuthorities(jwt);
                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header("X-Username", username)
                                .header("X-Authorities", String.join(",", authorities))
                                .build();
                        exchange = exchange.mutate().request(mutatedRequest).build();
                    }
                } catch (Exception e) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
        }
        return chain.filter(exchange);
    }
}
