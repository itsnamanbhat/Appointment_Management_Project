package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.DiagnosticCenter;

@Component
public class DiagnosticCenterMapper {
	public DiagnosticCenter mapToDiagnosticCenter(DiagnosticCenterDto dDto) {
		return new DiagnosticCenter(dDto.getId(),dDto.getName(),dDto.getContactNO(),dDto.getAddress(),dDto.getDiagnosticTests(),dDto.getAppointments(),dDto.getUser());
	}
	
	public DiagnosticCenterDto mapToDiagnosticCenterDto(DiagnosticCenter d) {
		return new DiagnosticCenterDto(d.getId(),d.getName(),d.getContactNO(),d.getAddress(),d.getDiagnosticTests(),d.getAppointments(),d.getUser());
	}
}
