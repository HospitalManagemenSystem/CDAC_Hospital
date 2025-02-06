package com.hms.entity;

import java.time.LocalDateTime;

import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.entity.Appointment.AppointmentType;

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

    @Column(name = "status")
    private String status;
    
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

    
	public Appointment(LocalDateTime appointmentTime, Doctor doctor, Patient patient) {
		super();
		this.appointmentTime = appointmentTime;
		this.doctor = doctor;
		this.patient = patient;
	}
    @Override
    public String toString() {
        return "Appointment{" +
               "appointmentTime=" + appointmentTime +
               ", appointmentType=" + appointmentType +
               ", doctor=" + (doctor != null ? doctor.getId() : "null") +
               '}';
    }

    public Patient getPatient() {
		return patient;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
	
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
}
