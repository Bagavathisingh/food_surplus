package com.pm.securitybackend.filter;

import com.pm.securitybackend.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(@NotNull  HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String Authorization =request.getHeader("Authorization");
        if (Authorization != null && Authorization.startsWith("Bearer ")){
            String token = Authorization.substring(7);
            if(jwtUtil.ValidateJwt(token)){
                String email = jwtUtil.ExtractMailFromJwt(token);
                String role = jwtUtil.ExtractRoleFromJwt(token);
                var Authorities = List.of(new SimpleGrantedAuthority("ROLE_"+role));
                var auth =new UsernamePasswordAuthenticationToken(email,null, Authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}
