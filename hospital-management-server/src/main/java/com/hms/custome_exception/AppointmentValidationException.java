package com.hms.custome_exception;

public class AppointmentValidationException extends RuntimeException {
	
	  public AppointmentValidationException(String message) {
	        super(message);
	    }
}
