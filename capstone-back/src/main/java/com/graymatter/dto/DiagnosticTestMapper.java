package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.DiagnosticTest;

@Component
public class DiagnosticTestMapper {
	
	public DiagnosticTest mapToDiagnosticTest(DiagnosticTestDto test) {
		return new DiagnosticTest(test.getId(),test.getTestName(),test.getTestPrice(),test.getDiagnosticCenter(),test.getAppointments());
	}
	public DiagnosticTestDto mapToDiagnosticTestDto(DiagnosticTest test) {
		return new DiagnosticTestDto(test.getId(),test.getTestName(),test.getTestPrice(),test.getDiagnosticCenter(),test.getAppointments());
	}
	
	
}
