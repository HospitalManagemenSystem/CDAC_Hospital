package com.hms.exception;

public class UserHandlingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserHandlingException(String message) {
        super(message);
    }
}
