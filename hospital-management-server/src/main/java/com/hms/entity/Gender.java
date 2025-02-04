package com.hms.entity;

public enum Gender {
	MALE, FEMALE, OTHER;
	
	@Override
	public String toString() {
		return name();
	}
}