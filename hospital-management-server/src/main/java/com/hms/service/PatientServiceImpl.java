package com.hms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.custome_exception.UserHandlingException;
import com.hms.dto.ApiResponse;
import com.hms.dto.PatientDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Patient;
import com.hms.repo.AdminRepository;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.PatientRepository;
import com.hms.service.DoctorService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private DoctorRepository doctorRepo;
    @Autowired
    private AppointmentRepository appointmentRepo;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse savePatient(PatientDTO patientDTO) {
        validateUniqueEmail(patientDTO.getEmail());
        
        Patient newPatient = modelMapper.map(patientDTO, Patient.class);
        newPatient.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        Patient savedPatient = patientRepo.save(newPatient);
        return new ApiResponse("Patient successfully created.", convertToDTO(savedPatient));
    }

    private PatientDTO convertToDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public ApiResponse<PatientDTO> updatePatientDetails(PatientDTO patientDTO, Long id) {
        Patient existingPatient = patientRepo.findById(id)
                .orElseThrow(() -> new UserHandlingException("Invalid patient ID: " + id));

        // Use ModelMapper to map PatientDTO to existing Patient entity
        modelMapper.map(patientDTO, existingPatient);

        // Save the updated patient details
        Patient updatedPatient = patientRepo.save(existingPatient);

        // Return the ApiResponse with the updated PatientDTO
        return new ApiResponse<>("Patient details updated successfully", convertToDTO(updatedPatient));
    }


    @Override
    public List<Appointment> getUpcomingAppointments(Long patientId) {
        Patient patient = getPatientDetails(patientId);
        return appointmentRepo.findByPatientAndAppointmentTimeAfter(patient, LocalDateTime.now());
    }

    @Override
    public List<Appointment> getPastAppointments(Long patientId) {
        Patient patient = getPatientDetails(patientId);
        return appointmentRepo.findByPatientAndAppointmentTimeBeforeOrderByAppointmentTimeDesc(patient, LocalDateTime.now());
    }

    @Override
    public Patient getPatientDetails(Long id) {
        if (id == null) {
            throw new UserHandlingException("Patient ID cannot be null");
        }
        return patientRepo.findById(id)
                .orElseThrow(() -> new UserHandlingException("Patient not found with ID: " + id));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public ApiResponse deletePatientById(Long patientId) {
        Patient patient = getPatientDetails(patientId);

        // Handle future appointments
        List<Appointment> futureAppointments = appointmentRepo.findByPatientAndAppointmentTimeAfter(patient, LocalDateTime.now());

        if (!futureAppointments.isEmpty()) {
            throw new UserHandlingException("Cannot delete patient with future appointments");
        }

        // Get all appointments for the patient
        List<Long> appointments = appointmentRepo.getAppointmentIdListForPatient(patientId);
        appointments.forEach(doctorService::makeSlotsAvailable);

        patientRepo.deleteById(patientId);
        return new ApiResponse("Successfully deleted patient with ID: " + patientId);
    }

    @Override
    public Patient authenticatePatient(String email, String password) {
        return patientRepo.findByEmailAndPassword(email, passwordEncoder.encode(password))
                .orElseThrow(() -> new UserHandlingException("Invalid credentials"));
    }

    private void validateUniqueEmail(String email) {
        if (doctorRepo.findByEmail(email).isPresent()) {
            throw new UserHandlingException("Email already registered with a doctor account");
        }
        if (adminRepo.findByEmail(email).isPresent()) {
            throw new UserHandlingException("Email already registered with an admin account");
        }
        if (patientRepo.findByEmail(email).isPresent()) {
            throw new UserHandlingException("Email already registered with a patient account");
        }
    }
}
