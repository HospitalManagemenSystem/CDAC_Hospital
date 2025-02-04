package com.hms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hms.dto.DoctorDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "doctor_tbl")
public class Doctor extends User {

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Language must be supplied")
    private String languages;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Specialization must be supplied")
    private String specialization;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Qualification must be supplied")
    private String qualification;

    private Integer fees;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private DoctorTimeTable timeSlot;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.DOCTOR; // Default role as DOCTOR

    public Doctor() {}

    // Constructor matching SQL table structure
    public Doctor(String languages, String specialization, String qualification, String username,
                  String firstName, String lastName, String email, String password, LocalDate dob,
                  Gender gender, String mobileNumber, String area, String city, String state, Integer fees) {
        super(username, firstName, lastName, email, password, dob, gender, mobileNumber, area, city, state);
        this.languages = languages;
        this.specialization = specialization;
        this.qualification = qualification;
        this.fees = fees;
    }

    // Getters and Setters
    public String getLanguages() { return languages; }
    public void setLanguages(String languages) { this.languages = languages; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public Integer getFees() { return fees; }
    public void setFees(Integer fees) { this.fees = fees; }

    public DoctorTimeTable getTimeSlot() { return timeSlot; }
    public void setTimeSlot(DoctorTimeTable timeSlot) { this.timeSlot = timeSlot; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    @Override
    public String toString() {
        return "Doctor [languages=" + languages + ", specialization=" + specialization +
                ", qualification=" + qualification + ", fees=" + fees + ", role=" + role + "]";
    }
}
