package com.hms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Doctor extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
    private UserRole role = UserRole.DOCTOR;

    // Default constructor
    public Doctor() {
    }

    // Getters
    public User getUser() {
        return user;
    }

    public String getLanguages() {
        return languages;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public Integer getFees() {
        return fees;
    }

    public DoctorTimeTable getTimeSlot() {
        return timeSlot;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public UserRole getRole() {
        return role;
    }

    // Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public void setTimeSlot(DoctorTimeTable timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // Helper method to add appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setDoctor(this);
    }

    // Helper method to remove appointment
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setDoctor(null);
    }

    @Override
    public String toString() {
        return "Doctor [user=" + user.getFirstName() + " " + user.getLastName() +
               ", languages=" + languages +
               ", specialization=" + specialization +
               ", qualification=" + qualification +
               ", fees=" + fees +
               ", role=" + role + "]";
    }
}
