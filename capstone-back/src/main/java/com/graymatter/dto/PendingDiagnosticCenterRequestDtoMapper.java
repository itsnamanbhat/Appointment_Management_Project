package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.PendingDiagnosticCenterRequest;

@Component
public class PendingDiagnosticCenterRequestDtoMapper {

	public PendingDiagnosticCenterRequest mapToPendingDiagnosticCenterRequest(PendingDiagnosticCenterRequestDto pdcr) {
		return new PendingDiagnosticCenterRequest(pdcr.getId(),pdcr.isApproved(),pdcr.getUsername(),pdcr.getEmail(),pdcr.getPassword());
	}
	
	public PendingDiagnosticCenterRequestDto mapToPendingDiagnosticCenterRequestDto(PendingDiagnosticCenterRequest pdcr) {
		return new PendingDiagnosticCenterRequestDto(pdcr.getId(),pdcr.isApproved(),pdcr.getUsername(),pdcr.getEmail(),pdcr.getPassword());
	}
}