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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.custome_exception.UserHandlingException;
import com.hms.dto.DoctorDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.DoctorTimeTable;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.DoctorTimeTableRepository;
import com.hms.repo.PatientRepository;

@Service
public class DoctorService {
    
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

    // Get specializations by city
    public List<String> getSpecializationsByCity(String city) {
        return doctorRepository.getSpecializationsByCity(city);
    }

    // Get doctors by specialization and city
    public List<Doctor> getDoctorsBySpecializationAndCity(String specialization, String city) {
        return doctorRepository.findBySpecializationAndCity(specialization, city);
    }
    
    public List<Doctor> getAllDoctors(){
    	
    	return doctorRepository.findAll();
    }
    
    public Doctor getDoctorDetails(Long doctorId) {
    	
    	return doctorRepository.findById(doctorId).orElseThrow(() -> new UserHandlingException("Invalid doctor Id:  " + doctorId));
    }
    
    public String deleteDoctorById(Long doctorId) {
    	
    	doctorRepository.deleteById(doctorId);
    	return "Successfully deleted doctor with Id: " + doctorId;
    }
    
    public Doctor saveDoctor(DoctorDTO doctorDTO) {
    	
    	String email = doctorDTO.getEmail();
    	
    	if(patientRepository.findByEmail(email).isPresent() || adminRepository.findByEmail(email).isPresent()) {
    		
    		throw new UserHandlingException("Email is already registered with another user.");
        }
    	
    	Doctor newDoctor = Doctor.createDoctor(doctorDTO);
    	newDoctor.setPassword(passwordEncoder.encode(newDoctor.getPassword()));
    	
    	return doctorRepository.save(newDoctor);
    }
    
    public void makeSlotsAvailable(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new UserHandlingException("Invalid appointment ID"));

        Doctor doctor = appointment.getDoctor();
        LocalDateTime appointmentTime = appointment.getAppointmentTime();

        doctor.getTimeSlot().getAvailableSlots().put(appointmentTime, true);
    }
    
    public List<LocalDateTime> createAvailableSlotsDetails(Long doctorId, DoctorTimeTable appointmentSlot) {
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

        Set<String> holidays = new HashSet<>(appointmentSlot.getHolidays());

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (holidays.contains(date.getDayOfWeek().toString())) continue;

            for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(slotDuration)) {
                LocalDateTime slot = LocalDateTime.of(date, time);
                slotMap.put(slot, !time.equals(breakTime));
                if (!time.equals(breakTime)) availableSlots.add(slot);
            }
        }

        appointmentSlot.setAvailableSlots(slotMap);
        doctor.setTimeSlot(doctorTimeTableRepository.save(appointmentSlot));

        return availableSlots;
    }
    
    public Doctor updateDoctorDetails(DoctorDTO doctorDTO, Long id) {
        Doctor existingDoctor = doctorRepository.findById(id)
            .orElseThrow(() -> new UserHandlingException("Invalid doctor ID: " + id));

        Doctor updatedDoctor = Doctor.createDoctor(doctorDTO);
        updatedDoctor.setId(id);
        updatedDoctor.setPassword(existingDoctor.getPassword());
        updatedDoctor.setTimeSlot(existingDoctor.getTimeSlot());

        BeanUtils.copyProperties(updatedDoctor, existingDoctor, getNullPropertyNames(updatedDoctor));

        return doctorRepository.save(existingDoctor);
    }
}
