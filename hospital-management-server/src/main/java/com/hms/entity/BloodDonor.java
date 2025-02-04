package com.hms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "blood_donor_tbl")
public class BloodDonor extends BaseEntity {
	 @Column(length = 50)
	    @NotBlank(message = "Name is required")
	    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	    private String name;

	    @Column(length = 50, unique = true)
	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    private String email;

	    @Column(length = 10)
	    @NotBlank(message = "Contact number is required")
	    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
	    private String contactNumber;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "blood_group")
	    private BloodGroup bloodGroup;

	    @Column(length = 50)
	    @NotBlank(message = "City is required")
	    private String city;

	    @Column(length = 50)
	    @NotBlank(message = "State is required")
	    private String state;

	    public BloodDonor() {}

	    // Getters and setters (omitted for brevity)

	    @Override
	    public String toString() {
	        return "BloodDonor{" +
	               "name='" + name + '\'' +
	               ", email='" + email + '\'' +
	               ", contactNumber='" + contactNumber + '\'' +
	               ", bloodGroup=" + bloodGroup +
	               ", city='" + city + '\'' +
	               ", state='" + state + '\'' +
	               '}';
	    }
}
