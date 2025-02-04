package com.hms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hms.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Find a doctor by email and password (for authentication)
    Optional<Doctor> findByEmailAndPassword(String email, String password);

    // Find a doctor by email
    Optional<Doctor> findByEmail(String email);

    // Get list of doctors by specialization and city
    List<Doctor> findBySpecializationAndCity(String specialization, String city);

    // Get list of unique specializations available in a given city
    @Query("SELECT DISTINCT d.specialization FROM Doctor d WHERE d.city = :city")
    List<String> getSpecializationsByCity(String city);
}
