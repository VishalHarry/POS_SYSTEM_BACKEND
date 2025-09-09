package com.posSystem.Configuration;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class JwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // 1. Get Authorization header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt == null || !jwt.startsWith("Bearer ")) {
            // No token → continue without authentication
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extract token (remove "Bearer ")
        String token = jwt.substring(7);

        try {
            // TODO: Yaha tum JWT library (like io.jsonwebtoken - jjwt) se token validate karoge
            // Abhi ke liye assume karte hain token == "valid-token"
            if ("valid-token".equals(token)) {
                // 3. Create Authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                "user",          // principal (username/id)
                                null,            // credentials (not needed after login)
                                List.of(new SimpleGrantedAuthority("ROLE_USER")) // authorities
                        );

                // 4. Set authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5. Continue filter chain
        filterChain.doFilter(request, response);
    }
}
