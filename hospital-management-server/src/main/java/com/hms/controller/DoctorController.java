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
	
	@GetMapping("/specializations")
	public ResponseEntity<List<String>> getSpecializationsByCity(@RequestParam("city") String city){
		
		List<String> specializations = doctorService.getSpecializationsByCity(city);
		return new ResponseEntity<>(specializations, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getDoctorsBySpecializationAndCity(
			@RequestParam("specialization") String specialization,
			@RequestParam("city") String city){
		
		List<Doctor> doctors = doctorService.getDoctorsBySpecializationAndCity(specialization, city);
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@PostMapping("/createAppointmentSlot/{doctorId}")
	public List<LocalDateTime> createAppointmentSlots(@PathVariable Long doctorId,
			@RequestBody DoctorTimeTable doctorTimeTable) {
		return doctorService.createAvailableSlotsDetails(doctorId, doctorTimeTable);
	}

	@GetMapping("/getDoctorDetails/{doctorId}")
	public ResponseEntity<?> getDoctorDetails(@PathVariable Long doctorId) {
		return ResponseEntity.ok(doctorService.getDoctorDetails(doctorId));
	}

	@PutMapping("/updateDoctor/{doctorId}")
	public ResponseEntity<?> updateDoctorDetails(@RequestBody DoctorDTO detachedDoctor, @PathVariable Long doctorId) {
		return ResponseEntity.ok(doctorService.updateDoctorDetails(detachedDoctor, doctorId));
	}
}
