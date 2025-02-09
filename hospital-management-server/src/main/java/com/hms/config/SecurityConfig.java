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
                // 3. Public endpoints - No authentication required
                request.requestMatchers(
                        "/auth/login",  // Login endpoint
                        "/home/generate-token", // Token generation endpoint
                        "/home/reset-password",  // Password reset endpoint
                        "/swagger-ui/**", // Swagger UI and API docs
                        "/v*/api-doc*/**"  // Swagger UI and API docs
                ).permitAll()  // Allow public access to the above endpoints

                // 4. Patient-related endpoints (assuming these are for all authenticated users)
                .requestMatchers("/patient/authenticate", "/patient/appointments/upcoming/**", 
                                 "/patient/appointments/past/**", "/patient/all", "/patient/details/**")
                .authenticated() // Require authentication for these endpoints

                // 5. Doctor-related endpoints (only accessible by doctors)
                .requestMatchers("/doctor/**")  // Doctor-specific actions
                .hasRole("DOCTOR")  // Only users with DOCTOR role can access this

                // 6. Admin-related endpoints (only accessible by admin)
                .requestMatchers("/patient/create", "/patient/update/**", "/patient/delete/**", "/patient/all")
                .hasRole("ADMIN")  // Only users with ADMIN role can access this

                // 7. Default case: any other requests require authentication
                .anyRequest().authenticated()
            )

            // 8. Session management (No session creation as we're using JWT)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // 9. Add custom JWT filter before any authentication filter
            .addFilterBefore(customJWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Build the security configuration
    }

    // Provide AuthenticationManager as a Spring bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
