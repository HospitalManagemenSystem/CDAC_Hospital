package com.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
	 private Long userId;
	    private String userFirstName;
	    private String userType;
	    private String token; // Added for JWT token
}
