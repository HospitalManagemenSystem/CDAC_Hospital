package com.hms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hms.custome_exception.UserHandlingException;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.DoctorTimeTable;
import com.hms.entity.Patient;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.PatientRepository;

public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
    private AppointmentRepository appointmentRepo;
    
    @Autowired
    private DoctorRepository doctorRepo;
    
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
	@Override
	public Object getPatientByAppointmentId(Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
	            .orElseThrow(() -> new RuntimeException("Appointment not found"));
	        return appointment.getPatient();
	}

	@Override
	public List<Appointment> getAllPatientCurrentAppointments(Long patientId) {
		 LocalDateTime currentDate = LocalDateTime.now();
	        return appointmentRepo.findByPatientIdAndAppointmentTimeGreaterThanEqual(patientId, currentDate);
	}

	@Override
	public List<Appointment> getAllPatientAppointmentsHistory(Long patientId) {
		LocalDateTime currentDate = LocalDateTime.now();
        return appointmentRepo.findByPatientIdAndAppointmentTimeLessThan(patientId, currentDate);
	}

	@Override
	public List<Appointment> getAllCurrentAppointmentsForDoctor(Long doctorId) {
		LocalDateTime currentDate = LocalDateTime.now();
        return appointmentRepo.findByDoctorIdAndAppointmentTimeGreaterThanEqual(doctorId, currentDate);
	}

	@Override
	public List<Appointment> getPatientAppointmentsHistoryForDoctor(Long doctorId, Long patientId) {
		 LocalDateTime currentDate = LocalDateTime.now();
	        return appointmentRepo.findByDoctorIdAndPatientIdAndAppointmentTimeLessThan(doctorId, patientId, currentDate);
	}

	@Override
	public List<Appointment> getAllAppointmentsHistoryForDoctor(Long doctorId) {
		LocalDateTime currentDate = LocalDateTime.now();
        return appointmentRepo.findByDoctorIdAndAppointmentTimeLessThan(doctorId, currentDate);
	}

	@Override
	public List<LocalDateTime> getAllAppointmentSlots(Long doctorId) {

		 Doctor doctor = doctorRepo.findById(doctorId)
			        .orElseThrow(() -> new RuntimeException("Doctor not found"));
		Map<LocalDateTime, Boolean> availableSlots = doctor.getTimeSlot().getAvailableSlots();
		List<LocalDateTime> list = new ArrayList<>();
		for (Map.Entry<LocalDateTime, Boolean> entry : availableSlots.entrySet()) {
			int currDate = LocalDate.now().getDayOfMonth();
			int currMonth = LocalDate.now().getMonthValue();
			int slotDate = entry.getKey().getDayOfMonth();
			int slotMonth = entry.getKey().getMonthValue();
			if (entry.getValue() == true && entry.getKey().isAfter(LocalDateTime.now()) && currDate == slotDate
					&& currMonth == slotMonth) { // send only list whose boolean value is true (not booked slots)
				list.add(entry.getKey());
			}
		}
		Collections.sort(list);

		return list;
	}

	@Override
	public List<LocalDateTime> bookAppointmentForPatient(Long doctorId, Long patientId, String stime) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime time = LocalDateTime.parse(stime, formatter);

		Doctor doctor = doctorRepo.findById(doctorId)
				.orElseThrow(() -> new UserHandlingException("Doctor not found...!!!"));

		Patient patient = patientRepo.findById(patientId)
				.orElseThrow(() -> new UserHandlingException("Patient not found...!!!"));

		DoctorTimeTable timeTable = doctor.getTimeSlot();

		Appointment appointment = new Appointment(time, doctor, patient);
		appointmentRepo.save(appointment);

		List<LocalDateTime> availableSlotList = timeTable.bookAvailableSlot(time);

		return availableSlotList;
	}

	@Override
	public String cancelAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
	            .orElseThrow(() -> new RuntimeException("Appointment not found"));
	            
	        if (appointment.getAppointmentTime().isBefore(LocalDateTime.now())) {
	            throw new RuntimeException("Cannot cancel past appointments");
	        }
	        
	        appointment.setStatus("CANCELLED");
	        appointmentRepo.save(appointment);
	        return "Appointment cancelled successfully";
	}

	@Override
	public Object getDoctorByAppointmentId(Long appointmentId) {
		 Appointment appointment = appointmentRepo.findById(appointmentId)
		            .orElseThrow(() -> new RuntimeException("Appointment not found"));
		        return appointment.getDoctor();
	}

}
