package com.hms.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hms.entity.Appointment;

public interface AppointmentService {
	Object getPatientByAppointmentId(Long appointmentId);
    List<Appointment> getAllPatientCurrentAppointments(Long patientId);
    List<Appointment> getAllPatientAppointmentsHistory(Long patientId);
    List<Appointment> getAllCurrentAppointmentsForDoctor(Long doctorId);
    List<Appointment> getPatientAppointmentsHistoryForDoctor(Long doctorId, Long patientId);
    List<Appointment> getAllAppointmentsHistoryForDoctor(Long doctorId);
    List<LocalDateTime> getAllAppointmentSlots(Long doctorId);
    List<LocalDateTime> bookAppointmentForPatient(Long doctorId, Long patientId, String time);
    String cancelAppointment(Long appointmentId);
    Object getDoctorByAppointmentId(Long appointmentId);
}
