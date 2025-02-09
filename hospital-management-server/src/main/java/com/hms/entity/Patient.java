package com.hms.entity;

import java.util.ArrayList;
import java.util.List;

import com.hms.dto.PatientDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient extends User {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroup bloodGroup;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return super.toString() + ", BloodGroup=" + bloodGroup;
    }

//    public static Patient createPatient(PatientDTO dto) {
//        Patient patient = new Patient();
//        patient.setFirstName(dto.getFirstName());
//        patient.setLastName(dto.getLastName());
//        patient.setEmail(dto.getEmail());
//        patient.setPassword(dto.getPassword());
//        patient.setMobileNumber(dto.getMobileNumber());
//        patient.setCity(dto.getCity());
//        patient.setBloodGroup(dto.getBloodGroup());
//        patient.setArea(dto.getArea());
//        patient.setGender(dto.getGender());
//        return patient;
//    }
}
