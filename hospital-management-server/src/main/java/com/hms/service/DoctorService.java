package com.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entity.Doctor;
import com.hms.repo.DoctorRepository;
import com.hms.repo.DoctorTimeTableRepository;

@Service
public class DoctorService {
    
	@Autowired
    private DoctorRepository doctorRepository;
	
	@Autowired
    private DoctorTimeTableRepository doctorTimeTableRepository;

    // Get specializations by city
    public List<String> getSpecializationsByCity(String city) {
        return doctorRepository.getSpecializationsByCity(city);
    }

    // Get doctors by specialization and city
    public List<Doctor> getDoctorsBySpecializationAndCity(String specialization, String city) {
        return doctorRepository.findBySpecializationAndCity(specialization, city);
    }
}
