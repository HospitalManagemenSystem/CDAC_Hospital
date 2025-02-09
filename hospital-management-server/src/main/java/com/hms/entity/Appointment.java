package com.hms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;

@Entity
@Table(name = "appointment_tbl")
public class Appointment extends BaseEntity {

    @Column(name = "appointment_time", nullable = false)
    @Future(message = "Appointment time must be in the future")
    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType appointmentType = AppointmentType.CLINIC_VISIT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "status", length = 20, nullable = false)
    private String status = "SCHEDULED";  // Default status

    public Appointment() {}

    public Appointment(LocalDateTime appointmentTime, Doctor doctor, Patient patient, String status) {
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
    }
    
    public Appointment(LocalDateTime appointmentTime, Doctor doctor, Patient patient) {
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
        this.status = "SCHEDULED"; // Default status
    }


	public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
               "appointmentTime=" + appointmentTime +
               ", appointmentType=" + appointmentType +
               ", status=" + status +
               '}';
    }



    public enum AppointmentType {
        CLINIC_VISIT, 
        EMERGENCY, 
        FOLLOW_UP, 
        ONLINE_CONSULTATION
    }
}
