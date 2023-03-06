package com.dng.bank.app.controller;

import com.dng.bank.app.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Object> dataIntegrity(HttpServletRequest req, DataIntegrityViolationException exception) {
		
		ErrorBody error = ErrorBody.builder()
			                  .timestamp(System.currentTimeMillis())
			                  .path(req.getRequestURI())
			                  .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			                  .error(exception.getCause().getCause().getMessage())
			                  .build();
		
		return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body(error);
	}
	
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<Object> entityNotFound(HttpServletRequest req, EntityNotFoundException exception) {
		ErrorBody error = ErrorBody.builder()
			                  .timestamp(System.currentTimeMillis())
			                  .path(req.getRequestURI())
			                  .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			                  .error(exception.getMessage())
			                  .build();
		
		return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body(error);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Object> logicError(HttpServletRequest req, IllegalArgumentException exception) {
		ErrorBody error = ErrorBody.builder()
			                  .timestamp(System.currentTimeMillis())
			                  .path(req.getRequestURI())
			                  .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			                  .error(exception.getMessage())
			                  .build();
		
		return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body(error);
	}
	
	@Builder
	@Data
	static class ErrorBody {
		long timestamp;
		int status;
		String error;
		String path;
	}
	
}
