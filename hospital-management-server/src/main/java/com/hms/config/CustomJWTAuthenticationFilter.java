package com.hms.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomJWTAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomJWTAuthenticationFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Check for the Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2. If the Authorization header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // 3. Extract the JWT token
            String jwt = authHeader.substring(7);

            try {
                // 4. Validate the JWT and get Authentication object
                Authentication authentication = jwtUtils.populateAuthenticationTokenFromJWT(jwt);

                // 5. If authentication is valid, set it in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Successfully set authentication in SecurityContextHolder");

            } catch (Exception e) {
                // 6. Handle JWT parsing/validation errors (invalid, expired tokens, etc.)
                logger.error("JWT validation failed: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired JWT token");
                return; // Exit the filter chain if the token is invalid
            }
        }

        // 7. Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
