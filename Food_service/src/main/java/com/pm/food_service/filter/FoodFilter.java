package com.pm.food_service.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FoodFilter extends OncePerRequestFilter {

    @Value("${api.gateway.key}")
    private String Api_Gateway_Key;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (!Api_Gateway_Key.equals(request.getHeader("X-Api-Gateway-Key"))){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("The request can't process by the application , the client is Unauthorized ");
            return;
        }

        filterChain.doFilter(request,response);
    }
}
