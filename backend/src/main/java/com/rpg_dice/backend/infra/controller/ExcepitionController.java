package com.rpg_dice.backend.infra.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rpg_dice.backend.application.exception.BusinessException;
import com.rpg_dice.backend.infra.dto.ExceptionDetailsDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExcepitionController {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionDetailsDTO> handleBusinessException(BusinessException e, HttpServletRequest request) {
		ExceptionDetailsDTO exceptionDetailsDTO = new ExceptionDetailsDTO(LocalDateTime.now(), 400, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(400).body(exceptionDetailsDTO);
	}
}