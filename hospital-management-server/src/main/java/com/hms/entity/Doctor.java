package com.hms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
