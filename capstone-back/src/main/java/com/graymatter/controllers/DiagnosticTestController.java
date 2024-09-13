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

import com.graymatter.dto.DiagnosticTestDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.DiagnosticTestService;

@RestController
@RequestMapping("/api/v1/")
public class DiagnosticTestController {

	@Autowired
	DiagnosticTestService service;
	
	@GetMapping("/diagnostictest")
	public ResponseEntity<?> getAllDiagnosticTestDto(){
		return service.getAllDiagnosticTestDto();
	}
	
	@PostMapping("/diagnostictest")
	public ResponseEntity<?> addNewDiagnosticTest(@RequestBody DiagnosticTestDto diagnosticTest) {
		return service.addNewDiagnosticTest(diagnosticTest);
	}
	
	@PutMapping("/diagnostictest/{id}")
	public ResponseEntity<?> updateDiagnosticTestDetails(@PathVariable("id") int id, @RequestBody DiagnosticTestDto diagnosticTest) throws IdNotFoundException {
		return service.updateDiagnosticTestDetails(id, diagnosticTest);
	}
	
	@GetMapping("/diagnostictest/{id}")
	public ResponseEntity<?> getDiagnosticTestById(@PathVariable("id") int id) throws IdNotFoundException {
		return service.getDiagnosticTestById(id);
	}
	
	@DeleteMapping("/diagnostictest/{id}")
	public ResponseEntity<?> deleteDiagnosticTestById(@PathVariable("id") int id) throws IdNotFoundException {
		return service.deleteDiagnosticTestById(id);
	}

	
	@GetMapping("/diagnostictest/center/{id}")
	public ResponseEntity<?> getTestsOfCenter(@PathVariable("id")int id) {
		return service.getTestsOfCenter(id);
	}
}
