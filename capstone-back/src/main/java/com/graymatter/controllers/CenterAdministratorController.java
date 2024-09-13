package com.graymatter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graymatter.dto.CenterAdministratorDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.CenterAdministratorService;

@RestController
@RequestMapping("/api/v1")
public class CenterAdministratorController {
	
	@Autowired
	CenterAdministratorService service;
	
	@PostMapping("/centerAdministrator")
	public ResponseEntity<?> addCenterAdministrator(@RequestBody CenterAdministratorDto centerAdministrator){
		return service.addCenterAdministrator(centerAdministrator);
	}
	
	@GetMapping("/centerAdministrator")
	public ResponseEntity<?> getAllCenterAdiministrators(){
		return service.getAllCenterAdiministrators();
	}
	@GetMapping("/centerAdministrator/{id}")
	public ResponseEntity<?> getCenterAdministratorById(@PathVariable("id") int centerAdministratorId) throws IdNotFoundException{
		return service.getCenterAdministratorById(centerAdministratorId);
	}
	@DeleteMapping("/centerAdministrator/{id}")
	public ResponseEntity<?> deleteCenterAdministrator(@PathVariable("id") int centerAdminId) throws IdNotFoundException{
		return service.deleteCenterAdministrator(centerAdminId);
	}
	
	@PutMapping("/centerAdministrator/{id}")
	public ResponseEntity<?> updateCenterAdministrator(@PathVariable int id, @RequestBody CenterAdministratorDto centerAdministrator){
		return service.updateCenterAdministrator(id, centerAdministrator);
	}
	@GetMapping("/centerAdministrator/user/{id}")
	public ResponseEntity<?> getCenterAdministratorByUserId(@PathVariable("id") int userId) throws IdNotFoundException{
		return service.getCenterAdministratorByUserId(userId);
	}
}
