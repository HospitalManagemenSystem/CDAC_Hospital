 package com.hms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.ApiResponse;
import com.hms.dto.DoctorDTO;
import com.hms.entity.Doctor;
import com.hms.entity.DoctorTimeTable;
import com.hms.service.DoctorService;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Endpoint to get specializations by city
    @GetMapping("/specializations")
    public ResponseEntity<ApiResponse<List<String>>> getSpecializationsByCity(@RequestParam("city") String city) {
        ApiResponse<List<String>> response = doctorService.getSpecializationsByCity(city);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to get doctors by specialization and city
    @GetMapping
    public ResponseEntity<ApiResponse<List<Doctor>>> getDoctorsBySpecializationAndCity(
            @RequestParam("specialization") String specialization,
            @RequestParam("city") String city) {
        ApiResponse<List<Doctor>> response = doctorService.getDoctorsBySpecializationAndCity(specialization, city);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to create appointment slots for a doctor
    @PostMapping("/createAppointmentSlot/{doctorId}")
    public ResponseEntity<ApiResponse<List<LocalDateTime>>> createAppointmentSlots(@PathVariable Long doctorId,
            @RequestBody DoctorTimeTable doctorTimeTable) {
        ApiResponse<List<LocalDateTime>> response = doctorService.createAvailableSlotsDetails(doctorId, doctorTimeTable);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint to get doctor details by ID
    @GetMapping("/getDoctorDetails/{doctorId}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorDetails(@PathVariable Long doctorId) {
        ApiResponse<Doctor> response = doctorService.getDoctorDetails(doctorId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to update doctor details
    @PutMapping("/updateDoctor/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorDTO>> updateDoctorDetails(@RequestBody DoctorDTO detachedDoctor,
            @PathVariable Long doctorId) {
        ApiResponse<DoctorDTO> response = doctorService.updateDoctorDetails(detachedDoctor, doctorId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
