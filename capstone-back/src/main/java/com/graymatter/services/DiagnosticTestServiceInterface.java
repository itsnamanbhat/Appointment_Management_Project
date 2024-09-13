package com.graymatter.services;


import org.springframework.http.ResponseEntity;

import com.graymatter.dto.DiagnosticTestDto;
import com.graymatter.exceptions.IdNotFoundException;

public interface DiagnosticTestServiceInterface {
	
	public ResponseEntity<?>  getAllDiagnosticTest();
	public ResponseEntity<?>  addNewDiagnosticTest(DiagnosticTestDto diagnosticTest);
	public ResponseEntity<?>  updateDiagnosticTestDetails(int id,DiagnosticTestDto diagnosticTest) throws IdNotFoundException;
	public ResponseEntity<?>  getDiagnosticTestById(int id) throws IdNotFoundException;
	public ResponseEntity<?>  deleteDiagnosticTestById(int id) throws IdNotFoundException;
	public ResponseEntity<?>  getAllDiagnosticTestDto();
	
	
}
