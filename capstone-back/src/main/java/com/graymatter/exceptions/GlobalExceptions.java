package com.graymatter.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions  {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException rnfe){
		return new ResponseEntity<>(rnfe.getMessage(),HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException rnfe){
		return new ResponseEntity<>(rnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<String> handleConflictException(ConflictException rnfe){
		return new ResponseEntity<>(rnfe.getMessage(),HttpStatus.CONFLICT);
	}
	@ExceptionHandler(UserOrEmailAlreadyPresent.class)
	public ResponseEntity<String> handleUserOrEmailAlreadyPresent(UserOrEmailAlreadyPresent rnfe){
		return new ResponseEntity<>(rnfe.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseEntity<String> handleHttpMessageNotWritableException(HttpMessageNotWritableException rnfe){
		return new ResponseEntity<>(rnfe.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException rnfe){
		return new ResponseEntity<>(rnfe.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<String> handleEmailNotFoundException(EmailNotFoundException rnfe){
		return new ResponseEntity<>(rnfe.getMessage(),HttpStatus.NOT_FOUND);
	}


}
