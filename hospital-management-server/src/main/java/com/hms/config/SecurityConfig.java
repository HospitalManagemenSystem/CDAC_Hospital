package com.hms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Equivalent to bean config XML
@EnableWebSecurity // Enables annotation support for Spring Security
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CustomJWTAuthenticationFilter customJWTAuthenticationFilter;

    // Configure the SecurityFilterChain bean to customize Spring Security filter chain
    @Bean
    public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
        // 1. Disable CSRF filter (since we're using JWT, not sessions)
        http.csrf(customizer -> customizer.disable())

            // 2. Configure URL-based access
            .authorizeHttpRequests(request -> 
                request.requestMatchers(
                        "/users/signup",  // Endpoint to sign up users
                        "/users/signin",   // Endpoint to sign in users
                        "/v*/api-doc*/**", // Swagger UI and API docs
                        "/swagger-ui/**",
                        "/users/forgot-password", // Assuming you have this endpoint
                        "/users/reset-password"  // Assuming you have this endpoint
                ).permitAll()  // Allow public access to the above endpoints
                .requestMatchers(HttpMethod.OPTIONS).permitAll() // Allow pre-flight requests (CORS)

                // 3. Protect these endpoints and require authentication
                .requestMatchers("/doctors/**") // Endpoint for Doctor-related actions (Doctor management)
                .hasRole("DOCTOR") // Only users with DOCTOR role can access this
                .requestMatchers("/patients/**") // Endpoint for Patient-related actions
                .hasRole("ADMIN")  // Only users with ADMIN role can access this
                .requestMatchers("/appointments/**") // Endpoint for Appointment-related actions
                .authenticated()  // Any authenticated user can access this
                .anyRequest().authenticated()  // All other requests require authentication
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No session creation as we're using JWT

            // 4. Add custom JWT filter before any authentication filter
            .addFilterBefore(customJWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Build the security configuration
    }

    // Provide AuthenticationManager as a Spring bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
