package com.hms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hms.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	 Optional<Patient> findByEmailAndPassword(String email, String password);
	    
	    Optional<Patient> findByEmail(String email);
	    @Query("SELECT COUNT(p) > 0 FROM Patient p WHERE p.email = :email")
	    boolean existsByEmail(String email);
}
