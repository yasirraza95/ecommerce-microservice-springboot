package com.springboot.ecommerce.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.ecommerce.user.enums.Response;
import com.springboot.ecommerce.user.response.ResponseHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity blogNotFoundException(NotFoundException blogNotFoundException) {
		return ResponseHandler.generateResponse(Response.FALSE, "Not found", null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
    	return ResponseHandler.generateResponse(Response.FALSE, "Error Occurred", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
