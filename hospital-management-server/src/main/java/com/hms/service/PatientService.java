package com.hms.service;

import java.util.List;

import com.hms.dto.ApiResponse;
import com.hms.dto.PatientDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Patient;

public interface PatientService {
//    ApiResponse savePatient(PatientDTO patientDTO);
    ApiResponse<PatientDTO> updatePatientDetails(PatientDTO patientDTO, Long patientId);  // updated return type
    Patient getPatientDetails(Long patientId);
    List<Patient> getAllPatients();
    ApiResponse deletePatientById(Long patientId);
    Patient authenticatePatient(String email, String password);
    List<Appointment> getUpcomingAppointments(Long patientId);
    List<Appointment> getPastAppointments(Long patientId);
}
