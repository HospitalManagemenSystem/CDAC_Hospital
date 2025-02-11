package com.hms.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Patient extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroup bloodGroup;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    // Default constructor
    public Patient() {
    }

    // Constructor with user and bloodGroup
    public Patient(User user, BloodGroup bloodGroup) {
        this.user = user;
        this.bloodGroup = bloodGroup;
    }

    // Constructor with all fields
    public Patient(User user, BloodGroup bloodGroup, List<Appointment> appointments) {
        this.user = user;
        this.bloodGroup = bloodGroup;
        this.appointments = appointments;
    }

    // Getter for user
    public User getUser() {
        return user;
    }

    // Setter for user
    public void setUser(User user) {
        this.user = user;
    }

    // Getter for bloodGroup
    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    // Setter for bloodGroup
    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    // Getter for appointments
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Setter for appointments
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    // Helper method to add appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPatient(this);
    }

    // Helper method to remove appointment
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setPatient(null);
    }

//    @Override
//    public String toString() {
//        return "Patient [userId=" + (user != null ? user.getId() : "null") + 
//               ", bloodGroup=" + bloodGroup + 
//               ", user=" + (user != null ? user.getFirstName() + " " + user.getLastName() : "null") + "]";
//    }
}
