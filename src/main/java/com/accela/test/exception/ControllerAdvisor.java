package com.accela.test.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		return getDefaultResponseEntity(e);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return getDefaultResponseEntity(e);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
		return getDefaultResponseEntity(e);
	}

	protected ResponseEntity<Object> getDefaultResponseEntity(Exception e) {
		return new ResponseEntity<>("Invalid request. More details: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
