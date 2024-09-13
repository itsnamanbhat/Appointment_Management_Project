package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.Appointment;

@Component
public class AppointmentMapper {

	public Appointment mapToAppointment(AppointmentDto a) {
		return new Appointment(a.getId(),a.getAppointmentDate(),a.getDiagnosticTests(),a.getPatient(),a.getDiagnosticCenter(),a.getTestResults());
	}
	public AppointmentDto mapToAppointmentDto(Appointment a) {
		return new AppointmentDto(a.getId(),a.getAppointmentDate(),a.getDiagnosticTests(),a.getPatient(),a.getDiagnosticCenter(),a.getTestResults());
	}
	
}
