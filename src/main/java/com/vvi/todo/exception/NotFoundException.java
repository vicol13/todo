package com.vvi.todo.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(final String message) {
		super(message);
	}
}
