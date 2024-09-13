package com.graymatter.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.graymatter.dto.DiagnosticCenterDto;
import com.graymatter.exceptions.IdNotFoundException;

interface DiagnsoticCenterServiceInterface {
	public ResponseEntity<?> getAllDiagnosticCenters();
	public ResponseEntity<?> addDiagnosticCenter(DiagnosticCenterDto diagnosticCenter);
	public ResponseEntity<?> getDiagnosticCenterById(int diagnosticCenterId) throws IdNotFoundException;
	public ResponseEntity<?> updateDiagnosticCenter(int id,DiagnosticCenterDto diagnosticCenter) throws IdNotFoundException;
	public ResponseEntity<?> viewTestResults(int diagnosticCentreId,String testName);
	public ResponseEntity<?> addTest(int diagnosticCentreId, int testId );
	public ResponseEntity<?> removeDiagnosticCenter(int diagnosticCentreId) throws IdNotFoundException;
	public ResponseEntity<?> getListOfAppointments(String centerName);
//	public ResponseEntity<?> findByDiagnosticTests(Set<DiagnosticTest> diagnosticTests);
//	public ResponseEntity<?> findByDiagnosticTests(List<DiagnosticTest> diagnosticTests);
	public ResponseEntity<?> findByDiagnosticTests(List<String> diagnosticTestNames);
	
}
