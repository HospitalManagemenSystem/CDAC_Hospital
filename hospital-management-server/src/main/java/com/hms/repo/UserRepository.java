package com.hms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Load user details by email
    Optional<User> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);
}
