package com.vvi.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {NotFoundException.class})
	protected ResponseEntity<ErrorMessage> handleConflict(NotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), Instant.now().toString()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {BadRequestException.class})
	protected ResponseEntity<ErrorMessage> handleBadRequest(BadRequestException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage(), Instant.now().toString()));
	}

	@ExceptionHandler(value = {IllegalArgumentException.class})
	protected ResponseEntity<ErrorMessage> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
		return ResponseEntity.badRequest()
			.body(new ErrorMessage(ex.getMessage(), Instant.now().toString()));
	}

}
