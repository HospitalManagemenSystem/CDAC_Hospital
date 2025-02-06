package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class AppointmentSlot {

	@NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be present or future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @Min(value = 10, message = "Slot duration must be at least 10 minutes")
    @Max(value = 120, message = "Slot duration cannot exceed 120 minutes")
    private int slotDuration;

    private LocalTime breakTime;

    @Min(value = 0, message = "Break duration cannot be negative")
    private int breakDuration;

    private List<String> holidays;
}