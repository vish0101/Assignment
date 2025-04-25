package com.app.exception;


@SuppressWarnings("serial")
public class ValidationError extends RuntimeException {
	public ValidationError(String message) {
		super(message);
	}
}
