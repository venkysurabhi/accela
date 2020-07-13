package com.accela.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
        super("Resource not found with given info.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
