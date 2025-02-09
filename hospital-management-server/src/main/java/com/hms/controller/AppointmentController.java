package com.hms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.entity.Appointment;
import com.hms.service.AppointmentService;
import com.hms.service.DoctorService;
import com.hms.service.EmailSenderService;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppointmentController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/patient/{appointmentId}")
    public ResponseEntity<?> getPatientByAppointmentId(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getPatientByAppointmentId(appointmentId));
    }

    @GetMapping("/specialization/{city}")
    public ResponseEntity<?> getSpecializationByCity(@PathVariable String city) {
        return ResponseEntity.ok(doctorService.getSpecializationsByCity(city));
    }

    @GetMapping("/search/{specialization}/{city}")
    public ResponseEntity<?> getDoctorsBySpecializationAndCity(@PathVariable String specialization,
            @PathVariable String city) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecializationAndCity(specialization, city));
    }

    @GetMapping("/currAppointmentP/{patientId}")
    public ResponseEntity<?> getAllCurrentAppointmentsForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAllPatientCurrentAppointments(patientId));
    }

    @GetMapping("/appointementHistoryP/{patientId}")
    public ResponseEntity<?> getAllAppointmentsHistoryForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAllPatientAppointmentsHistory(patientId));
    }

    @GetMapping("/currAppointmentD/{doctorId}")
    public ResponseEntity<?> getAllCurrentAppointmentsForDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAllCurrentAppointmentsForDoctor(doctorId));
    }

    @GetMapping("/appointementHistoryD/{doctorId}/{patientId}")
    public ResponseEntity<?> getAppointmentsHistoryForPatientForDoctor(@PathVariable Long doctorId,
            @PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getPatientAppointmentsHistoryForDoctor(doctorId, patientId));
    }

    @GetMapping("/appointementHistoryD/{doctorId}")
    public ResponseEntity<?> getAllAppointmentsHistoryForDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsHistoryForDoctor(doctorId));
    }

    @GetMapping("/getAppointmentSlots/{doctorId}")
    public ResponseEntity<?> getAllAppointmentSlots(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentSlots(doctorId));
    }

    @GetMapping("/bookAppointment/{doctorId}/{patientId}/{time}")
    public ResponseEntity<?> bookAppointmentForPatient(@PathVariable Long doctorId, @PathVariable Long patientId,
            @PathVariable String time) {
        emailSenderService.sendEmailOnAppointmentBooking(patientId, time);
        return ResponseEntity.ok(appointmentService.bookAppointmentForPatient(doctorId, patientId, time));
    }

    @DeleteMapping("/cancelAppointment/{appointmentId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long appointmentId) {
        emailSenderService.sendEmailOnCancelAppointment(appointmentId);
        return ResponseEntity.ok(appointmentService.cancelAppointment(appointmentId));
    }

    @GetMapping("/doctor/{appointmentId}")
    public ResponseEntity<?> getDoctorByAppointmentId(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getDoctorByAppointmentId(appointmentId));
    }
}
