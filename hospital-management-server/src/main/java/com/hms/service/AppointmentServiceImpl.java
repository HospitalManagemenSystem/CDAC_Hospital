package com.hms.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.custome_exception.UserHandlingException;
import com.hms.dto.ApiResponse;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.DoctorTimeTable;
import com.hms.entity.Patient;
import com.hms.repo.AppointmentRepository;
import com.hms.repo.DoctorRepository;
import com.hms.repo.PatientRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse getPatientByAppointmentId(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new UserHandlingException("Appointment not found"));
        return new ApiResponse("Patient details retrieved successfully", appointment.getPatient());
    }

    @Override
    public ApiResponse getDoctorByAppointmentId(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new UserHandlingException("Appointment not found"));
        return new ApiResponse("Doctor details retrieved successfully", appointment.getDoctor());
    }

    @Override
    public ApiResponse getAllPatientCurrentAppointments(Long patientId) {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepo.findByPatientIdAndAppointmentTimeGreaterThanEqual(patientId, currentDate);
        return new ApiResponse("Current appointments retrieved successfully", appointments);
    }

    @Override
    public ApiResponse getAllPatientAppointmentsHistory(Long patientId) {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepo.findByPatientIdAndAppointmentTimeLessThan(patientId, currentDate);
        return new ApiResponse("Past appointment history retrieved successfully", appointments);
    }

    @Override
    public ApiResponse getAllCurrentAppointmentsForDoctor(Long doctorId) {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentTimeGreaterThanEqual(doctorId, currentDate);
        return new ApiResponse("Current appointments retrieved successfully", appointments);
    }

    @Override
    public ApiResponse getAllAppointmentsHistoryForDoctor(Long doctorId) {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentTimeLessThan(doctorId, currentDate);
        return new ApiResponse("Past appointment history retrieved successfully", appointments);
    }

    @Override
    public ApiResponse getPatientAppointmentsHistoryForDoctor(Long doctorId, Long patientId) {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndPatientIdAndAppointmentTimeLessThan(doctorId, patientId, currentDate);
        return new ApiResponse("Past appointments for patient retrieved successfully", appointments);
    }

    @Override
    public ApiResponse getAllAppointmentSlots(Long doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new UserHandlingException("Doctor not found"));

        Map<LocalDateTime, Boolean> availableSlots = doctor.getTimeSlot().getAvailableSlots();
        List<LocalDateTime> slots = new ArrayList<>();

        for (Map.Entry<LocalDateTime, Boolean> entry : availableSlots.entrySet()) {
            if (entry.getValue() && entry.getKey().isAfter(LocalDateTime.now())) {
                slots.add(entry.getKey());
            }
        }
        Collections.sort(slots);

        return new ApiResponse("Available appointment slots retrieved successfully", slots);
    }

    @Override
    public ApiResponse bookAppointmentForPatient(Long doctorId, Long patientId, String stime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(stime, formatter);

        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new UserHandlingException("Doctor not found"));

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new UserHandlingException("Patient not found"));

        DoctorTimeTable timeTable = doctor.getTimeSlot();

        if (!timeTable.getAvailableSlots().containsKey(time) || !timeTable.getAvailableSlots().get(time)) {
            throw new UserHandlingException("Selected time slot is not available");
        }

        Appointment appointment = new Appointment(time, doctor, patient, "SCHEDULED");
        appointmentRepo.save(appointment);
        timeTable.bookAvailableSlot(time);

        return new ApiResponse("Appointment booked successfully", time);
    }

    @Override
    public ApiResponse cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new UserHandlingException("Appointment not found"));

        if (appointment.getAppointmentTime().isBefore(LocalDateTime.now())) {
            throw new UserHandlingException("Cannot cancel past appointments");
        }

        appointment.setStatus("CANCELLED");
        appointmentRepo.save(appointment);

        return new ApiResponse("Appointment cancelled successfully", null);
    }
}
