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

import com.graymatter.dto.PatientDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.PatientService;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
	@Autowired
	PatientService service;
	
	@PostMapping("/patient")
	public ResponseEntity<?> registerPatient(@RequestBody PatientDto patient){
		return service.registerPatient(patient);
	}
	@DeleteMapping("/patient/{id}")
	public ResponseEntity<?> deletePatientById(@PathVariable("id") int id) throws IdNotFoundException{
		return service.deletePatientById(id);
	}
	@PutMapping("/patient/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable("id") int id,@RequestBody PatientDto patient) throws IdNotFoundException{
		return service.updatePatient(id, patient);
	}
	@GetMapping("/patient/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable("id") int id) throws IdNotFoundException{
		return service.getPatientById(id);
	}
	@GetMapping("/patients")
	public ResponseEntity<?> getAllPatients(){
		return service.getAllPatients()	;
	}
	
	@GetMapping("/patient/testresult/username/{username}")
	public ResponseEntity<?> getAllTestResult(@PathVariable("username") String patientUserName){
		return service.getAllTestResult(patientUserName);
	}
	@GetMapping("/patient/username/{username}")
	public ResponseEntity<?> viewPatientByUsername(@PathVariable("username") String patientUsername){
		return service.viewPatientByUsername(patientUsername);
	}
	@GetMapping("/patient/mobile/{mobile}")
	public ResponseEntity<?> viewPatientByMobileNo(@PathVariable("mobile") String mobile){
		return service.viewPatientByMobileNo(mobile);
	}

}
