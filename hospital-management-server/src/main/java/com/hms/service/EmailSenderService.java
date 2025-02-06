package com.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import com.hms.repo.AppointmentRepository;
import com.hms.entity.*;

public class EmailSenderService {

	private static final String SENDER_EMAIL = "ask.your.doctor.springboot.app@gmail.com";
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSimpleEmail(String toEmail, String body, String subject) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(SENDER_EMAIL);
		mailMessage.setTo(toEmail);
		mailMessage.setText(body);
		mailMessage.setSubject(subject);
		
		mailSender.send(mailMessage);
		System.out.println("Mail send successfully...");
	}
	
	public void sendEmailOnAppointmentBooking(Long patientId, String time) {
		
		Patient patient = patientService.getPatientDetails(patientId);
		sendSimpleEmail(patient.getEmail(), 
				"Your appointment has been successfully booked at " + time,
                "Appointment Confirmation");
	}
}
