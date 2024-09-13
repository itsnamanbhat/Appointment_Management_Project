package com.graymatter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graymatter.dto.ContactUsDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.ContactUsService;

@RestController
@RequestMapping("/api/v1")
public class ContactusController {
	
	@Autowired 
	ContactUsService service;
	
	@PostMapping("/contactus")
	public ResponseEntity<?> addMessage(@RequestBody ContactUsDto c)
	{
		return service.addMessage(c);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMessage (@PathVariable("id") int id) throws IdNotFoundException
	{
		return service.deleteMessage(id);
	}
	
}
