package com.graymatter.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.graymatter.dto.DiagnosticCenterDto;
import com.graymatter.entities.DiagnosticTest;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.DiagnosticCenterService;

@RestController
@RequestMapping("/api/v1")
public class DiagnosticCenterController {
	
Logger logger= LoggerFactory.getLogger(DiagnosticCenterController.class);

	@Autowired
	DiagnosticCenterService service;
	
	@GetMapping("/diagnosticcenter")
	public ResponseEntity<?> getAllDiagnosticCenters(){
		logger.trace("FATAL ERROR");
		return service.getAllDiagnosticCenters();
		}
	
	@PostMapping("/diagnosticcenter")
	public ResponseEntity<?> addDiagnosticCenter(@RequestBody DiagnosticCenterDto diagnosticCenter){
		return service.addDiagnosticCenter(diagnosticCenter);
	}
	@GetMapping("/diagnosticcenter/{id}")
	public ResponseEntity<?> getDiagnosticCenterById(@PathVariable("id") int diagnosticCenterId) throws IdNotFoundException{
		return service.getDiagnosticCenterById(diagnosticCenterId);
	}
	@GetMapping("/diagnosticcenter/user/{id}")
	public ResponseEntity<?> getDiagnosticCenterByUserId(@PathVariable("id") int id) throws IdNotFoundException{
		return service.getDiagnosticCenterByUserId(id);
	}
	@PutMapping("/diagnosticcenter/{id}")
	public ResponseEntity<?> updateDiagnosticCenter(@PathVariable("id") int id,@RequestBody DiagnosticCenterDto diagnosticCenter) throws IdNotFoundException{
		return service.updateDiagnosticCenter(id,diagnosticCenter);
	}
	
	@GetMapping("/diagnosticcenter/viewTestResult/{id}/{testname}")
	public ResponseEntity<?> viewTestResults(@PathVariable("id")int diagnosticCentreId,@PathVariable("testname") String testName){
		return service.viewTestResults(diagnosticCentreId, testName);
	}
	
	@PostMapping("/diagnosticcenter/addtest/{id}/{testid}")
	public ResponseEntity<?> addTest(@PathVariable("id") int diagnosticCentreId,@PathVariable("testid") int testId ){
		return service.addTest(diagnosticCentreId, testId);
	}
	@DeleteMapping("/diagnosticcenter/{id}")
	public ResponseEntity<?> removeDiagnosticCenter(@PathVariable("id") int diagnosticCentreId) throws IdNotFoundException{
		return service.removeDiagnosticCenter(diagnosticCentreId);

	}

	@GetMapping("/diagnosticcenter/centername/{centername}")
	public ResponseEntity<?> getListOfAppointments(@PathVariable("centername") String centerName){
		return service.getListOfAppointments(centerName);
	}
//	@PostMapping("/diagnosticcenter/tests")
//	public ResponseEntity<?> findByDiagnosticTests(@RequestBody List<Integer> diagnosticTests){
//		return service.findByDiagnosticTests(diagnosticTests);
//	}
	
	@PostMapping("/diagnosticcenter/tests")
	public ResponseEntity<?> findByDiagnosticTests(@RequestBody List<String> diagnosticTests){
		return service.findByDiagnosticTests(diagnosticTests);
	}
	

}
