package com.hms.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hms.dto.ApiResponse;
import com.hms.entity.Appointment;

public interface AppointmentService {
    ApiResponse<?> getPatientByAppointmentId(Long appointmentId);
    
    ApiResponse getAllPatientCurrentAppointments(Long patientId);
    
    ApiResponse getAllPatientAppointmentsHistory(Long patientId);
    
    ApiResponse getAllCurrentAppointmentsForDoctor(Long doctorId);
    
    ApiResponse getAllAppointmentsHistoryForDoctor(Long doctorId);
    
    ApiResponse getPatientAppointmentsHistoryForDoctor(Long doctorId, Long patientId);
    
    ApiResponse getAllAppointmentSlots(Long doctorId);
    
    ApiResponse bookAppointmentForPatient(Long doctorId, Long patientId, String stime);
    
    ApiResponse<String> cancelAppointment(Long appointmentId);
    
    ApiResponse<?> getDoctorByAppointmentId(Long appointmentId);
}
