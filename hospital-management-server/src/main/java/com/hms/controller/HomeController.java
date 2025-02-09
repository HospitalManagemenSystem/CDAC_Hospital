package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hms.dto.ApiResponse;
import com.hms.dto.LoginRequest;
import com.hms.dto.LoginResponse;
import com.hms.service.HomeService;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@RequestBody LoginRequest loginRequest) {
        ApiResponse<LoginResponse> response = homeService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    // Endpoint for generating token
    @PostMapping("/generate-token")
    public ResponseEntity<ApiResponse<Long>> generateToken(@RequestParam String email) {
        ApiResponse<Long> response = homeService.generateToken(email);
        return ResponseEntity.ok(response);
    }

    // Endpoint for resetting password
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestParam String userEmail,
                                                              @RequestParam String userNewPassword) {
        ApiResponse<String> response = homeService.resetPassword(userEmail, userNewPassword);
        return ResponseEntity.ok(response);
    }
}
