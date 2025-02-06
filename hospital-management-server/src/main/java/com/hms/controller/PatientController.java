package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.PatientDTO;
import com.hms.service.PatientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/patients")
@CrossOrigin(origins = "*")
@Tag(name = "Patient Management")
@RequiredArgsConstructor
@Validated
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@PostMapping("/patientSignUp")
	public ResponseEntity<?> savePatient(@RequestBody @Valid PatientDTO patient) {
		PatientDTO p = patientService.savePatient(patient);
		if(p == null) {
			return ResponseEntity.badRequest().body(null);
		}
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}

	@GetMapping("/getPatientDetails/{patientId}")
	public ResponseEntity<?> getPatientDetails(@PathVariable Long patientId) {
		return ResponseEntity.ok(patientService.getPatientDetails(patientId));
	}

	@PutMapping("/updatePatientDetails/{patientId}")
	public ResponseEntity<?> updatePatientDetails(@RequestBody PatientDTO detachedPatient,
			@PathVariable Long patientId) {
		return ResponseEntity.ok(patientService.updatePatientDetails(detachedPatient, patientId));
	}
}
