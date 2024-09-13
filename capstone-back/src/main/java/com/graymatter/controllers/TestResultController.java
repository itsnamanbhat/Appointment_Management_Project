package com.graymatter.controllers;

import java.util.List;

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
import com.graymatter.dto.TestResultDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.TestResultService;

@RestController
@RequestMapping("/api/v1/")
public class TestResultController {

	@Autowired
	TestResultService service;
	
	@GetMapping("/testresults")
	public ResponseEntity<?> getAllTestResults(){
		return service.getAllTestResults();
	}
	
	@PostMapping("/testresult")
	public ResponseEntity<?> addTestResult(@RequestBody TestResultDto testResult) {
		return service.addTestResult(testResult);
	}
	
	@PutMapping("/testresult/{id}")
	public ResponseEntity<?> updateTestResult(@PathVariable("id") int id,@RequestBody TestResultDto testResult) throws IdNotFoundException {
		return service.updateTestResult(id, testResult);
	}
	
	@GetMapping("/testresult/{id}")
	public ResponseEntity<?> getTestResultById(@PathVariable("id")int id) throws IdNotFoundException {
		return service.getTestResultById(id);
	}
	
	@DeleteMapping("/testresult/{id}")
	public ResponseEntity<?> deleteTestResultById(@PathVariable("id")int id) throws IdNotFoundException
	{
		 return service.deleteTestResultById(id);
	}
	
	@GetMapping("/testresult/patient/{patient_id}")
	public ResponseEntity<?> viewTestResultByPatient(@PathVariable("patient_id")int patient_id){
		return service.viewTestResultByPatient(patient_id);
	}
}
