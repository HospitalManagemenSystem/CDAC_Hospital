package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.ApiResponse;
import com.hms.dto.DoctorDTO;
import com.hms.entity.BloodDonor;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.service.BloodDonorService;
import com.hms.service.DoctorService;
import com.hms.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private BloodDonorService bloodDonorService;
    
    // Sign up a new doctor
    @PostMapping("/doctorSignUp")
    public ResponseEntity<ApiResponse<Void>> saveDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
        ApiResponse<Void> response = doctorService.saveDoctor(doctorDTO);
        if (response.getData() == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Failed to save doctor", null));
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all doctor details
    @GetMapping("/getAllDoctors")
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctorDetails() {
        ApiResponse<List<Doctor>> response = doctorService.getAllDoctors();
        return ResponseEntity.ok(response);
    }

    // Delete doctor by ID
    @DeleteMapping("/removeDoctor/{doctorId}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long doctorId) {
        ApiResponse<Void> response = doctorService.deleteDoctorById(doctorId);
        return ResponseEntity.ok(response);
    }

    // Get all patient details
    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatientDetails() {
        return patientService.getAllPatients();
    }

    // Delete patient by ID
    @DeleteMapping("/removePatient/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable Long patientId) {
        ApiResponse response = patientService.deletePatientById(patientId);
        return ResponseEntity.ok(response.getMessage());
    }

    // Get all blood donors
    @GetMapping("/searchDonors")
    public List<BloodDonor> getAllBloodDonors() {
        return bloodDonorService.getAllBloodDonors();
    }
}
<<<<<<< HEAD

 
=======
>>>>>>> b9fa34ed620e3bdb4980b07a08112492ce7e064f
