package com.hms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.custome_exception.UserHandlingException;
import com.hms.dto.ApiResponse;
import com.hms.dto.DoctorDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.DoctorTimeTable;
import com.hms.repo.AdminRepository;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.DoctorTimeTableRepository;
import com.hms.repo.PatientRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
    
	@Autowired
    private DoctorRepository doctorRepository;
	
	@Autowired
    private DoctorTimeTableRepository doctorTimeTableRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private ModelMapper modelMapper;
	
	// Convert Entity to DTO
    private DoctorDTO convertToDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    // Get specializations by city
    public ApiResponse<List<String>> getSpecializationsByCity(String city) {
        List<String> specializations = doctorRepository.getSpecializationsByCity(city);
        return new ApiResponse<>("Specializations fetched successfully", specializations);
    }

    // Get doctors by specialization and city
    public ApiResponse<List<Doctor>> getDoctorsBySpecializationAndCity(String specialization, String city) {
        List<Doctor> doctors = doctorRepository.findBySpecializationAndCity(specialization, city);
        return new ApiResponse<>("Doctors fetched successfully", doctors);
    }
    
    public ApiResponse<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return new ApiResponse<>("Doctors fetched successfully", doctors);
    }
    
    public ApiResponse<Doctor> getDoctorDetails(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new UserHandlingException("Invalid doctor Id:  " + doctorId));
        return new ApiResponse<>("Doctor details fetched successfully", doctor);
    }
    
    // Delete a doctor by ID
    public ApiResponse<Void> deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
        return new ApiResponse<>("Successfully deleted doctor with Id: " + doctorId, null);
    }
    
    // Save a new doctor
    public ApiResponse<Void> saveDoctor(DoctorDTO doctorDTO) {
        String email = doctorDTO.getEmail();

        if (patientRepository.findByEmail(email).isPresent() || adminRepository.findByEmail(email).isPresent()) {
            throw new UserHandlingException("Email is already registered with another user.");
        }

        Doctor newDoctor = modelMapper.map(doctorDTO, Doctor.class); // Using ModelMapper for conversion
        newDoctor.setPassword(passwordEncoder.encode(newDoctor.getPassword()));
        doctorRepository.save(newDoctor);

        return new ApiResponse<>("Doctor successfully created.", null);
    }
    
    // Make slots available for appointment
    public ApiResponse<Void> makeSlotsAvailable(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new UserHandlingException("Invalid appointment ID"));

        Doctor doctor = appointment.getDoctor();
        LocalDateTime appointmentTime = appointment.getAppointmentTime();

        doctor.getTimeSlot().getAvailableSlots().put(appointmentTime, true);
        return new ApiResponse<>("Appointment slots made available.", null);
    }
    
    public ApiResponse<List<LocalDateTime>> createAvailableSlotsDetails(Long doctorId, DoctorTimeTable appointmentSlot) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new UserHandlingException("Doctor not found"));

        Map<LocalDateTime, Boolean> slotMap = new HashMap<>();
        List<LocalDateTime> availableSlots = new ArrayList<>();

        LocalDate startDate = appointmentSlot.getStartDate();
        LocalDate endDate = appointmentSlot.getEndDate();
        LocalTime startTime = appointmentSlot.getStartTime();
        LocalTime endTime = appointmentSlot.getEndTime();
        LocalTime breakTime = appointmentSlot.getBreakTime();
        int slotDuration = appointmentSlot.getSlotDuration();

        // Convert LocalDate to String
        Set<String> holidays = new HashSet<>();
        for (LocalDate holiday : appointmentSlot.getHolidays()) {
            holidays.add(holiday.toString());  // Convert each LocalDate to String
        }

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            // Skip holidays
            if (holidays.contains(date.toString())) continue;

            for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(slotDuration)) {
                LocalDateTime slot = LocalDateTime.of(date, time);
                slotMap.put(slot, !time.equals(breakTime));  // Mark slot availability
                if (!time.equals(breakTime)) availableSlots.add(slot);  // Add to available slots if it's not the break time
            }
        }

        appointmentSlot.setAvailableSlots(slotMap);  // Save available slots in the appointment slot object
        doctor.setTimeSlot(doctorTimeTableRepository.save(appointmentSlot));  // Persist the doctor time table

        return new ApiResponse<>("Available slots created successfully", availableSlots);
    }

    public ApiResponse<DoctorDTO> updateDoctorDetails(DoctorDTO doctorDTO, Long id) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new UserHandlingException("Invalid doctor ID: " + id));

        modelMapper.map(doctorDTO, existingDoctor);
        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return new ApiResponse<>("Doctor details updated successfully", convertToDTO(updatedDoctor));
    }
}
