package com.hms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.entity.Doctor;
import com.hms.entity.User;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    Doctor findByUser(User user);
    
    Optional<Doctor> findByUserEmail(String email);
    
    // Get doctors by specialization and city
    @Query("SELECT d FROM Doctor d WHERE d.specialization = :specialization AND d.user.city = :city")
    List<Doctor> findBySpecializationAndCity(String specialization, String city);

    // Get unique specializations for a city
    @Query("SELECT DISTINCT d.specialization FROM Doctor d JOIN d.user u WHERE u.city = :city")
    List<String> getSpecializationsByCity(@Param("city") String city);
}


