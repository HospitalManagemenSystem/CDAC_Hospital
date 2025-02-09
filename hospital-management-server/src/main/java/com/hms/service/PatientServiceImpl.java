package com.hms.service;
import static com.hms.util.UtilityClass.getNullPropertyNames;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.repo.AdminRepository;
//import com.hms.repo.AdminRepository;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.PatientRepository;

import com.hms.custome_exception.UserHandlingException;
import com.hms.dto.PatientDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Patient;

import jakarta.validation.Valid;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{
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
    
	@Override
	public PatientDTO savePatient(@Valid PatientDTO patientDTO) {
validateUniqueEmail(patientDTO.getEmail());
        
        Patient newPatient = Patient.createPatient(patientDTO);
        newPatient.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        Patient savedPatient =patientRepo.save(newPatient);
        return convertToDTO(savedPatient);
	}

	private PatientDTO convertToDTO(Patient patient) {
	    PatientDTO dto = new PatientDTO();
	    dto.setFirstName(patient.getFirstName());
	    dto.setLastName(patient.getLastName());
	    dto.setEmail(patient.getEmail());
	    dto.setMobileNumber(patient.getMobileNumber());
	    dto.setCity(patient.getCity());
	    dto.setBloodGroup(patient.getBloodGroup());
	    dto.setArea(patient.getArea());
	    dto.setGender(patient.getGender());
	    return dto;
	}
	@Override
	public Patient updatePatientDetails(@Valid PatientDTO patientDTO, Long id) {
Patient existingPatient = getPatientDetails(id);
        
        Patient updatedPatient = Patient.createPatient(patientDTO);
        updatedPatient.setId(id);
        updatedPatient.setPassword(existingPatient.getPassword());
        
        BeanUtils.copyProperties(updatedPatient, existingPatient, getNullPropertyNames(updatedPatient));
        return patientRepo.save(existingPatient);
	}
	 @Override
	    public List<Appointment> getUpcomingAppointments(Long patientId) {
	        Patient patient = getPatientDetails(patientId);
	        return appointmentRepo.findByPatientAndAppointmentTimeAfter(patient, LocalDateTime.now());
	    }
	 @Override
	    public List<Appointment> getPastAppointments(Long patientId) {
	        Patient patient = getPatientDetails(patientId);
	        return appointmentRepo.findByPatientAndAppointmentTimeBeforeOrderByAppointmentTimeDesc(
	            patient, LocalDateTime.now());
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
	public String deletePatientById(Long patientId) {
		 Patient patient = getPatientDetails(patientId);
		 // Handle future appointments
	        List<Appointment> futureAppointments = 
	            appointmentRepo.findByPatientAndAppointmentTimeAfter(patient, LocalDateTime.now());
	        
	        if (!futureAppointments.isEmpty()) {
	            throw new UserHandlingException("Cannot delete patient with future appointments");
	        }
	        // Get all appointments for the patient
	        List<Long> appointments = appointmentRepo.getAppointmentIdListForPatient(patientId);
	        appointments.forEach(doctorService::makeSlotsAvailable);

	        patientRepo.deleteById(patientId);
        return "Successfully deleted patient with ID: " + patientId;
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
