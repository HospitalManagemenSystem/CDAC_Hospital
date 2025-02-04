package com.hms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;

@Entity
@Table(name = "appointment_tbl")
public class Appointment extends BaseEntity {
	@Column(name = "appointment_time")
    @Future(message = "Appointment time must be in the future")
    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type")
    private AppointmentType appointmentType = AppointmentType.CLINIC_VISIT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Appointment() {}

    public enum AppointmentType {
        CLINIC_VISIT, 
        EMERGENCY, 
        FOLLOW_UP, 
        ONLINE_CONSULTATION
    }

    // Getters and setters (omitted for brevity)

    @Override
    public String toString() {
        return "Appointment{" +
               "appointmentTime=" + appointmentTime +
               ", appointmentType=" + appointmentType +
               '}';
    }
}
