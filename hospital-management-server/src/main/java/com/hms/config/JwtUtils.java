package com.hms.config;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.hms.custome_exception.JwtValidationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

    @Value("${spring.security.jwt.secret.key}")
    private String jwtSecret;

    @Value("${spring.security.jwt.exp.time}")
    private int jwtExpirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Generate JWT token after successful authentication
    public String generateJwtToken(Authentication authentication) {
        log.info("Generating JWT token for authenticated user: {}", authentication);

        CustomUserDetailsImpl userPrincipal = (CustomUserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // Username as the subject
                .setIssuedAt(new Date()) // Current date as issued date
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Expiration time
                .claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities())) // Custom claim for authorities
                .claim("user_id", userPrincipal.getUserEntity().getId()) // Custom claim for user id
                .signWith(key, SignatureAlgorithm.HS512) // Signing the JWT with HS512 algorithm
                .compact(); // Build the JWT token
    }

    // Get the username from the JWT token
    public String getUserNameFromJwtToken(Claims claims) {
        return claims.getSubject();
    }

    // Validate JWT token and extract the claims
    public Claims validateJwtToken(String jwtToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (Exception e) {
            log.error("Invalid JWT token", e);
            throw new JwtValidationException("Invalid JWT token", e); // Custom exception
        }
    }

    // Convert authorities to a comma-separated string
    private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    // Extract authorities from the claims in the JWT
    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        String authString = (String) claims.get("authorities");
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
    }

    // Extract user id from the JWT claims
    public Long getUserIdFromJwtToken(Claims claims) {
        return Long.valueOf((String) claims.get("user_id"));
    }

    // Create an Authentication token from JWT claims
    public Authentication populateAuthenticationTokenFromJWT(String jwt) {
        Claims payloadClaims = validateJwtToken(jwt);

        String username = getUserNameFromJwtToken(payloadClaims);
        List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
        Long userId = getUserIdFromJwtToken(payloadClaims);

        // Return a new authentication token with username, userId, and authorities
        return new UsernamePasswordAuthenticationToken(username, userId, authorities);
    }
}

