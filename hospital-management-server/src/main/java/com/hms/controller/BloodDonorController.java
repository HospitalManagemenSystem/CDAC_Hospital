package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.entity.BloodDonor;
import com.hms.service.BloodDonorService;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/blood-donors")
@CrossOrigin(origins = "*")
@Tag(name = "Blood Donor Management")
@RequiredArgsConstructor
@Validated
public class BloodDonorController {
@Autowired
private BloodDonorService bloodDonorService;

@GetMapping("/search")
public List<BloodDonor> searchDonors(
        @RequestParam String city,
        @RequestParam String bloodGroup) {
    List<BloodDonor> donors = bloodDonorService.getAllBloodDonorsByCityAndBloodGroup(city, bloodGroup);
    return  bloodDonorService.getAllBloodDonorsByCityAndBloodGroup(city, bloodGroup);
}
}
