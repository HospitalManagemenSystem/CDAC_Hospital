package com.hms.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hms.dto.ErrorResponse;
import com.hms.custome_exception.AppointmentValidationException;
import com.hms.custome_exception.UserHandlingException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//	@ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(new ApiResponse(ex.getMessage(), false), HttpStatus.NOT_FOUND);
//    }
//    
    @ExceptionHandler(UserHandlingException.class)
    public ResponseEntity<?> handleAuthenticationException(UserHandlingException ex) {
    	ErrorResponse resp = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }
//    
//    @ExceptionHandler(AppointmentValidationException.class)
//    public ResponseEntity<ApiResponse> handleAppointmentValidationException(AppointmentValidationException ex) {
//        return new ResponseEntity<>(new ApiResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
//    }
    
}

