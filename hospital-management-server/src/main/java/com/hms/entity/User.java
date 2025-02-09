package com.hms.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    @Column(length = 30, unique = true)
    @NotBlank(message = "User name must be supplied")
    private String username;

    @Column(length = 30)
    @NotBlank(message = "User first name must be supplied")
    private String firstName;

    @Column(length = 30)
    @NotBlank(message = "User last name must be supplied")
    private String lastName;

    @Column(length = 30, unique = true)
    @NotBlank(message = "User email must be supplied")
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 10, nullable = false)
    @NotBlank(message = "User mobile required")
    private String mobileNumber;

    private String area;
    private String city;
    private String state;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
