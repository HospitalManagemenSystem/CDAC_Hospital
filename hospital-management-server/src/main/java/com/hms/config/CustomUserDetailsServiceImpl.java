package com.hms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.entity.User;
import com.hms.repo.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    // Logger initialization
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    // Dependency injection
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Loading user by email: {}", email);

        // Invoke DAO's method to find the user by email
        User userEntity = userEntityRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User with email {} not found", email);
                    return new UsernameNotFoundException("Email not found: " + email);
                });

        // Return custom UserDetails implementation
        return new CustomUserDetailsImpl(userEntity);
    }
}
