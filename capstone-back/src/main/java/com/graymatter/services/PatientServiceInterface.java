package com.graymatter.services;


import org.springframework.http.ResponseEntity;

import com.graymatter.dto.PatientDto;
import com.graymatter.exceptions.IdNotFoundException;

public interface PatientServiceInterface {
	public ResponseEntity<?> registerPatient(PatientDto patient);
	public ResponseEntity<?> deletePatientById(int id) throws IdNotFoundException;
	public ResponseEntity<?> updatePatient(int id,PatientDto patient) throws IdNotFoundException;
	public ResponseEntity<?> getPatientById(int id) throws IdNotFoundException;
	public ResponseEntity<?> getAllPatients();
	public ResponseEntity<?> getAllTestResult(String patientUserName);
	public ResponseEntity<?> viewPatientByUsername(String patientUsername);
	ResponseEntity<?> viewPatientByMobileNo(String mobileNo);

	
}
