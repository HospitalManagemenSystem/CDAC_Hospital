package com.hms.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.hms.dto.ErrorResponse;
import com.hms.custome_exception.UserHandlingException;
import com.hms.custome_exception.AppointmentValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle UserHandlingException (authentication errors, invalid user input, etc.)
    @ExceptionHandler(UserHandlingException.class)
    public ResponseEntity<ErrorResponse> handleUserHandlingException(UserHandlingException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);  // 400 Bad Request
    }

    // Handle AppointmentValidationException (validation errors for appointments)
    @ExceptionHandler(AppointmentValidationException.class)
    public ResponseEntity<ErrorResponse> handleAppointmentValidationException(AppointmentValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);  // 400 Bad Request
    }

    // Generic exception handler for all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Internal server error: " + ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);  // 500 Internal Server Error
    }
}
