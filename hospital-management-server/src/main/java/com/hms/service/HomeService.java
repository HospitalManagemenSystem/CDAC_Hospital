package com.hms.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.custome_exception.UserHandlingException;
import com.hms.dto.ApiResponse;
import com.hms.dto.LoginRequest;
import com.hms.dto.LoginResponse;
import com.hms.entity.Admin;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.repo.AdminRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.PatientRepository;

@Service
@Transactional
public class HomeService {

    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
    private final AdminRepository adminRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeService(AdminRepository adminRepo, PatientRepository patientRepo,
                       PasswordEncoder passwordEncoder, DoctorRepository doctorRepo,
                       EmailSenderService emailSenderService, ModelMapper modelMapper) {
        this.adminRepo = adminRepo;
        this.patientRepo = patientRepo;
        this.passwordEncoder = passwordEncoder;
        this.doctorRepo = doctorRepo;
        this.emailSenderService = emailSenderService;
        this.modelMapper = modelMapper;
    }

    public ApiResponse<LoginResponse> authenticateUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Admin admin = adminRepo.findByEmail(email).orElse(null);
            if (admin != null && password.equals(admin.getPassword())) {
                return new ApiResponse<>("Login successful", new LoginResponse(admin.getId(), admin.getName(), "admin", "dummy-jwt-token"));
            }
        } catch (Exception e) {
            logger.error("Admin login failed: {}", e.getMessage());
        }

        try {
            Patient patient = patientRepo.findByEmail(email).orElse(null);
            if (patient != null && passwordEncoder.matches(password, patient.getPassword())) {
                return new ApiResponse<>("Login successful", new LoginResponse(patient.getId(), patient.getFirstName(), "patient", "dummy-jwt-token"));
            }
        } catch (Exception e) {
            logger.error("Patient login failed: {}", e.getMessage());
        }

        try {
            Doctor doctor = doctorRepo.findByEmail(email).orElse(null);
            if (doctor != null && passwordEncoder.matches(password, doctor.getPassword())) {
                return new ApiResponse<>("Login successful", new LoginResponse(doctor.getId(), doctor.getFirstName(), "doctor", "dummy-jwt-token"));
            }
        } catch (Exception e) {
            logger.error("Doctor login failed: {}", e.getMessage());
        }

        throw new UserHandlingException("Invalid email or password");
    }

    public ApiResponse<Long> generateToken(String email) {
        boolean userExists = adminRepo.findByEmail(email).isPresent() ||
                             patientRepo.findByEmail(email).isPresent() ||
                             doctorRepo.findByEmail(email).isPresent();
        
        if (!userExists) {
            throw new UserHandlingException("Invalid Email!!!");
        }
        
        Long token = (long) (Math.random() * (9999 - 1000 + 1) + 1000);
        logger.info("Generated Token: {}", token);
        emailSenderService.sendEmailTokenToResetPassword(email, token);
        
        return new ApiResponse<>("Token generated successfully", token);
    }

    public ApiResponse<String> resetPassword(String userEmail, String userNewPassword) {
        try {
            Patient patient = patientRepo.findByEmail(userEmail).orElse(null);
            if (patient != null) {
                if (passwordEncoder.matches(userNewPassword, patient.getPassword())) {
                    return new ApiResponse<>("New password cannot be the same as the old password");
                }
                patient.setPassword(passwordEncoder.encode(userNewPassword));
                return new ApiResponse<>("Password updated successfully");
            }
        } catch (Exception e) {
            logger.error("Patient password reset failed: {}", e.getMessage());
        }

        try {
            Doctor doctor = doctorRepo.findByEmail(userEmail).orElse(null);
            if (doctor != null) {
                if (passwordEncoder.matches(userNewPassword, doctor.getPassword())) {
                    return new ApiResponse<>("New password cannot be the same as the old password");
                }
                doctor.setPassword(passwordEncoder.encode(userNewPassword));
                return new ApiResponse<>("Password updated successfully");
            }
        } catch (Exception e) {
            logger.error("Doctor password reset failed: {}", e.getMessage());
        }
        
        throw new UserHandlingException("Email not found");
    }
}
