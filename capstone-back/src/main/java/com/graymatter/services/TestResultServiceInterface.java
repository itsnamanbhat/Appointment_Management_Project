package com.graymatter.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.graymatter.dto.PatientDto;
import com.graymatter.dto.TestResultDto;
import com.graymatter.entities.Patient;
import com.graymatter.entities.TestResult;
import com.graymatter.exceptions.IdNotFoundException;

public interface TestResultServiceInterface {

	public ResponseEntity<?>  getAllTestResults();
	public ResponseEntity<?>  addTestResult(TestResultDto testResult); 
	public ResponseEntity<?>  updateTestResult(int id,TestResultDto testResult)throws IdNotFoundException;
	public ResponseEntity<?>  getTestResultById(int id)throws IdNotFoundException;
	public ResponseEntity<?>  deleteTestResultById(int id)throws IdNotFoundException;
	public ResponseEntity<?>  viewTestResultByPatient(int patient_id);
	
	
	
}
