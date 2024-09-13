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

import com.graymatter.dto.PendingDiagnosticCenterRequestDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;
import com.graymatter.services.PendingDiagnosticCenterRequestService;

@RestController
@RequestMapping("/api/v1")
public class PendingDiagnosticCenterRequestController {
	
	@Autowired
	PendingDiagnosticCenterRequestService service;

    @PostMapping("/request")
    public ResponseEntity<?> requestNewDiagnosticCenter(@RequestBody PendingDiagnosticCenterRequestDto request) throws UserOrEmailAlreadyPresent {
        return service.requestNewDiagnosticCenter(request);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveRequest(@PathVariable int id) throws IdNotFoundException {
        return service.approveRequest(id);
    }

    @GetMapping("/requests/pending")
    public ResponseEntity<?> getPendingRequests() {
        return service.getPendingRequests();
    }
    @GetMapping("/requests")
    public ResponseEntity<?> getAllRequests() {
        return service.getAllRequests();
    }
    
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable("id") int id) throws IdNotFoundException{
    	return service.deleteRequest(id);
    }
}
