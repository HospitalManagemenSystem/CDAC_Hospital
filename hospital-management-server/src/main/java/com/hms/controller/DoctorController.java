package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.entity.Doctor;
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
}
