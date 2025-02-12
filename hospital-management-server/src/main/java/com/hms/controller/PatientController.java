package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hms.dto.ApiResponse;
import com.hms.dto.PatientDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Patient;
import com.hms.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Endpoint to save a new patient
//    @PostMapping("/create")
//    public ResponseEntity<ApiResponse> savePatient(@RequestBody PatientDTO patientDTO) {
//        ApiResponse response = patientService.savePatient(patientDTO);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

    // Endpoint to update patient details
    @PutMapping("/update/{patientId}")
    public ResponseEntity<ApiResponse<PatientDTO>> updatePatientDetails(@RequestBody PatientDTO patientDTO, 
                                                                          @PathVariable Long patientId) {
        ApiResponse<PatientDTO> response = patientService.updatePatientDetails(patientDTO, patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to get patient details by ID
    @GetMapping("/details/{patientId}")
    public ResponseEntity<Patient> getPatientDetails(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientDetails(patientId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Endpoint to get all patients
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Endpoint to delete a patient by ID
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<ApiResponse> deletePatient(@PathVariable Long patientId) {
        ApiResponse response = patientService.deletePatientById(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint for patient authentication
    @PostMapping("/authenticate")
    public ResponseEntity<Patient> authenticatePatient(@RequestParam String email, @RequestParam String password) {
        Patient patient = patientService.authenticatePatient(email, password);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Endpoint to get upcoming appointments for a patient
    @GetMapping("/appointments/upcoming/{patientId}")
    public ResponseEntity<List<Appointment>> getUpcomingAppointments(@PathVariable Long patientId) {
        List<Appointment> appointments = patientService.getUpcomingAppointments(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Endpoint to get past appointments for a patient
    @GetMapping("/appointments/past/{patientId}")
    public ResponseEntity<List<Appointment>> getPastAppointments(@PathVariable Long patientId) {
        List<Appointment> appointments = patientService.getPastAppointments(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
