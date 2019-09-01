package com.rafael.customer.controllers.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rafael.customer.services.exceptions.NoDataFoundException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ NoDataFoundException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  NoDataFoundException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	 
	    ApiError apiError = 
	      new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), errors);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
