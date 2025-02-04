package com.hms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.DoctorTimeTable;

public interface DoctorTimeTableRepository extends JpaRepository<DoctorTimeTable, Long> {

}
