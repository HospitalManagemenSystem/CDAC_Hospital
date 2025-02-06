package com.hms.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	  // Patient related queries
    List<Appointment> findByPatientAndAppointmentTimeAfter(Patient patient, LocalDateTime time);
    
    List<Appointment> findByPatientAndAppointmentTimeBeforeOrderByAppointmentTimeDesc(
        Patient patient, LocalDateTime time);
    
    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient AND a.status = 'SCHEDULED'")
    List<Appointment> findScheduledAppointmentsForPatient(Patient patient);
    
    // Doctor related queries
    List<Appointment> findByDoctorAndAppointmentTimeAfter(Doctor doctor, LocalDateTime time);
    
    List<Appointment> findByDoctorAndAppointmentTimeBeforeOrderByAppointmentTimeDesc(
        Doctor doctor, LocalDateTime time);
    
    List<Appointment> findByDoctorAndPatientAndAppointmentTimeBeforeOrderByAppointmentTimeDesc(
        Doctor doctor, Patient patient, LocalDateTime time);
    
    // ID retrieval
    @Query("SELECT a.id FROM Appointment a WHERE a.patient.id = :patientId")    
    List<Long> getAppointmentIdListForPatient(Long patientId);
 // Find appointments for a patient after current date/time
    List<Appointment> findByPatientIdAndAppointmentTimeGreaterThanEqual(Long patientId, LocalDateTime currentTime);
    
    // Find appointments for a patient before current date/time (history)
    List<Appointment> findByPatientIdAndAppointmentTimeLessThan(Long patientId, LocalDateTime currentTime);
    
    // Find appointments for a doctor after current date/time
    List<Appointment> findByDoctorIdAndAppointmentTimeGreaterThanEqual(Long doctorId, LocalDateTime currentTime);
    
    // Find appointments for specific doctor and patient before current date/time
    List<Appointment> findByDoctorIdAndPatientIdAndAppointmentTimeLessThan(Long doctorId, Long patientId, LocalDateTime currentTime);
    
    // Find appointments for a doctor before current date/time (history)
    List<Appointment> findByDoctorIdAndAppointmentTimeLessThan(Long doctorId, LocalDateTime currentTime);
    
    // Find appointments for a specific doctor and time
    List<Appointment> findByDoctorIdAndAppointmentTime(Long doctorId, LocalDateTime appointmentTime);
    
    // Find all appointments for a doctor ordered by date
    List<Appointment> findByDoctorIdOrderByAppointmentTimeDesc(Long doctorId);
    
    // Find all appointments for a patient ordered by date
    List<Appointment> findByPatientIdOrderByAppointmentTimeDesc(Long patientId);
    
}
