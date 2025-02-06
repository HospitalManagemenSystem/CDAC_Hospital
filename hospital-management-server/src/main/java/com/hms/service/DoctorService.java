package com.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.custome_exception.UserHandlingException;
import com.hms.dto.DoctorDTO;
import com.hms.entity.Doctor;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.DoctorTimeTableRepository;
import com.hms.repo.PatientRepository;

@Service
public class DoctorService {
    
	@Autowired
    private DoctorRepository doctorRepository;
	
	@Autowired
    private DoctorTimeTableRepository doctorTimeTableRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    // Get specializations by city
    public List<String> getSpecializationsByCity(String city) {
        return doctorRepository.getSpecializationsByCity(city);
    }

    // Get doctors by specialization and city
    public List<Doctor> getDoctorsBySpecializationAndCity(String specialization, String city) {
        return doctorRepository.findBySpecializationAndCity(specialization, city);
    }
    
    public List<Doctor> getAllDoctors(){
    	
    	return doctorRepository.findAll();
    }
    
    public Doctor getDoctorDetails(Long doctorId) {
    	
    	return doctorRepository.findById(doctorId).orElseThrow(() -> new UserHandlingException("Invalid doctor Id:  " + doctorId));
    }
    
    public String deleteDoctorById(Long doctorId) {
    	
    	doctorRepository.deleteById(doctorId);
    	return "Successfully deleted doctor with Id: " + doctorId;
    }
    
    public Doctor saveDoctor(DoctorDTO doctorDTO) {
    	
    	String email = doctorDTO.getEmail();
    	
    	if(patientRepository.findByEmail(email).isPresent() || adminRepository.findByEmail(email).isPresent()) {
    		
    		throw new UserHandlingException("Email is already registered with another user.");
        }
    	
    	Doctor newDoctor = Doctor.createDoctor(doctorDTO);
    	newDoctor.setPassword(passwordEncoder.encode(newDoctor.getPassword()));
    	
    	return doctorRepository.save(newDoctor);
    }
}
