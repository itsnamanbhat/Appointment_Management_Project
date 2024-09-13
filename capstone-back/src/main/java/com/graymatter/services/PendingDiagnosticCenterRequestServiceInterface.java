package com.graymatter.services;



import org.springframework.http.ResponseEntity;

import com.graymatter.dto.PendingDiagnosticCenterRequestDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;

public interface PendingDiagnosticCenterRequestServiceInterface {
	public ResponseEntity<?> requestNewDiagnosticCenter(PendingDiagnosticCenterRequestDto request) throws UserOrEmailAlreadyPresent;
	public ResponseEntity<?> approveRequest(int id) throws IdNotFoundException;
	public ResponseEntity<?> getPendingRequests();
	public ResponseEntity<?> getAllRequests();
	public ResponseEntity<?> deleteRequest(int id) throws IdNotFoundException;
}
