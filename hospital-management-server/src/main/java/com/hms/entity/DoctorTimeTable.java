package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "doctor_timetable_tbl")
public class DoctorTimeTable extends BaseEntity {
    
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @Max(value = 30)
    @Min(value = 15)
    private int slotDuration;

    private LocalTime breakTime;

    @ElementCollection
    private List<LocalDate> holidays = new ArrayList<>();

    @ElementCollection
	private Map<LocalDateTime, Boolean> availableSlots = new HashMap<>();

    @OneToOne
    @JoinColumn(name = "doctor_id", unique = true)
    private Doctor doctor;

    public DoctorTimeTable() {}

    public DoctorTimeTable(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime,
                           int slotDuration, LocalTime breakTime, List<LocalDate> holidays) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotDuration = slotDuration;
        this.breakTime = breakTime;
        this.holidays = holidays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }

    public LocalTime getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(LocalTime breakTime) {
        this.breakTime = breakTime;
    }

    public List<LocalDate> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public Map<LocalDateTime, Boolean> getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(Map<LocalDateTime, Boolean> availableSlots) {
		this.availableSlots = availableSlots;
	}

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "DoctorTimeTable [startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime
                + ", endTime=" + endTime + ", slotDuration=" + slotDuration + ", breakTime=" + breakTime + "]";
    }
  //helper method to book available slots
  	public List<LocalDateTime> bookAvailableSlot(LocalDateTime time) {
  		Boolean value = availableSlots.get(time);
  		availableSlots.put(time, !value);
  		List<LocalDateTime> list = new ArrayList<>();
  		for (Map.Entry<LocalDateTime, Boolean> entry : availableSlots.entrySet()) {
  			if (entry.getValue() == true) { // send only list whose boolean value is true (not booked slots)
  				list.add(entry.getKey());
  			}
  		}
  		return list;
  	}
}
