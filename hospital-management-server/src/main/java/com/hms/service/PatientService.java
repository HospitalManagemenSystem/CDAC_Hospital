package com.hms.service;

import java.util.List;

import com.hms.dto.PatientDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Patient;

public interface PatientService {
	PatientDTO savePatient(PatientDTO patientDTO);
    Patient updatePatientDetails(PatientDTO patientDTO, Long patientId);
    Patient getPatientDetails(Long patientId);
    List<Patient> getAllPatients();
    String deletePatientById(Long patientId);
    Patient authenticatePatient(String email, String password);
    List<Appointment> getUpcomingAppointments(Long patientId);
    List<Appointment> getPastAppointments(Long patientId);
}
