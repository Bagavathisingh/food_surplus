package com.pm.api_gateway.JwtFilter;

import com.pm.api_gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {
    private final JwtUtil jwtUtil;

    @Value("${api.gateway.key}")
    private String Api_Gateway_Key;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if(path.startsWith("/api/auth")){

            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .header("X-Api-Gateway-Key",Api_Gateway_Key)
                    .build();
            ServerWebExchange requestExchange = exchange.mutate()
                    .request(request)
                    .build();

            return chain.filter(requestExchange);
        }
        String header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if(header == null || !header.startsWith("Bearer ")){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String authToken = header.substring(7);

        if(!jwtUtil.ValidateToken(authToken)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String userId = jwtUtil.ExtractUSer_id(authToken);
        String role = jwtUtil.ExtractRole(authToken);

        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("X-Api-Gateway-Key",Api_Gateway_Key)
                .header("X-User-Id",userId)
                .header("X-User-Role",role)
                .build();

        ServerWebExchange requestExchange = exchange
                .mutate()
                .request(request)
                .build();

        return chain.filter(requestExchange);
    }
}
