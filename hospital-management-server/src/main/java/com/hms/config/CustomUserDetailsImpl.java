package com.hms.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hms.entity.User;

public class CustomUserDetailsImpl implements UserDetails {
    
    private final User userEntity;

    public CustomUserDetailsImpl(User userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userEntity.getRole().name()));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    // Optional methods, you can implement them depending on your needs
    @Override
    public boolean isAccountNonExpired() {
        return true; // You can add actual logic based on your use case
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic if you want to lock accounts
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Check if you need to handle credentials expiration
    }

    @Override
    public boolean isEnabled() {
        return true; // You can change based on whether the user is active or not
    }

    public User getUserEntity() {
        return userEntity;
    }
}
