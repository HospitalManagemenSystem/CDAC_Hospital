package com.hms.service;

import java.time.LocalDateTime;
import java.util.List;
import com.hms.dto.ApiResponse;
import com.hms.dto.DoctorDTO;
import com.hms.entity.Doctor;
import com.hms.entity.DoctorTimeTable;
<<<<<<< HEAD
import com.hms.repo.AdminRepository;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.DoctorTimeTableRepository;
import com.hms.repo.PatientRepository;
=======
>>>>>>> b9fa34ed620e3bdb4980b07a08112492ce7e064f

public interface DoctorService {
    
    ApiResponse<List<String>> getSpecializationsByCity(String city);
    ApiResponse<List<Doctor>> getDoctorsBySpecializationAndCity(String specialization, String city);
    
    ApiResponse<List<Doctor>> getAllDoctors();
    ApiResponse<Doctor> getDoctorDetails(Long doctorId);
    ApiResponse<Void> deleteDoctorById(Long doctorId);
    ApiResponse<Void> saveDoctor(DoctorDTO doctorDTO);
    ApiResponse<Void> makeSlotsAvailable(Long appointmentId);

    ApiResponse<List<LocalDateTime>> createAvailableSlotsDetails(Long doctorId, DoctorTimeTable appointmentSlot);

    ApiResponse<DoctorDTO> updateDoctorDetails(DoctorDTO doctorDTO, Long id);
}