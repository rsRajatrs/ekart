package com.ekartv2.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ErrorMessageException.class)
	public ResponseEntity<String> handleErrorMessageException(ErrorMessageException exc)
	{
		
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException exc)
	{
		Map<String, String> errorMessage = new HashMap<>();
		exc.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String fieldError = error.getDefaultMessage();
			errorMessage.put(fieldName, fieldError);
		});
		return new ResponseEntity<Map<String,String>>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
