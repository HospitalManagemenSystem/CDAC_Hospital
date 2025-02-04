package com.hms.entity;

public enum UserRole {
    ADMIN, 
    DOCTOR, 
    PATIENT;

    @Override
    public String toString() {
        return name();
    }
}
