package com.malu.confeitaria.exceptions;

public class UsernameUniqueViolationException extends RuntimeException {
	public UsernameUniqueViolationException(String message) {
		super(message);
	}
}
