package com.hms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor_timetable_available_slots")
public class AvailableSlot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime slotTime;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "doctor_timetable_id")
    private DoctorTimeTable doctorTimeTable;

    public AvailableSlot() {}

    public AvailableSlot(LocalDateTime slotTime, boolean isAvailable, DoctorTimeTable doctorTimeTable) {
        this.slotTime = slotTime;
        this.isAvailable = isAvailable;
        this.doctorTimeTable = doctorTimeTable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(LocalDateTime slotTime) {
        this.slotTime = slotTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public DoctorTimeTable getDoctorTimeTable() {
        return doctorTimeTable;
    }

    public void setDoctorTimeTable(DoctorTimeTable doctorTimeTable) {
        this.doctorTimeTable = doctorTimeTable;
    }
}
