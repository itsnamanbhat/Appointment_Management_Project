package com.graymatter.services;

import org.springframework.http.ResponseEntity;

import com.graymatter.dto.ContactUsDto;
import com.graymatter.exceptions.IdNotFoundException;

public interface ContactUsServiceInterface {
	public ResponseEntity<?> addMessage(ContactUsDto c);
	public ResponseEntity<?> deleteMessage (int id) throws IdNotFoundException;
}
