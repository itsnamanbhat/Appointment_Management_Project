package com.graymatter.services;



import org.springframework.http.ResponseEntity;

import com.graymatter.dto.AppointmentDto;

import com.graymatter.exceptions.IdNotFoundException;

public interface AppointmentServiceInterface {

	public ResponseEntity<?> getAllAppointments();
	public ResponseEntity<?>  getAppointmentById(int id) throws IdNotFoundException;
	public ResponseEntity<?>  addAppointment(AppointmentDto appointment);
	public ResponseEntity<?>  deleteAppointmentById(int id) throws IdNotFoundException;
	public ResponseEntity<?>  updateAppointment(int id,AppointmentDto appointment) throws IdNotFoundException;
	public ResponseEntity<?>  getAllTestOfAppointment(int id) throws IdNotFoundException;
	public ResponseEntity<?>  getTestResultOfAppointment(int id) throws IdNotFoundException;
	public ResponseEntity<?>  getUpcomingAppointments();
	public ResponseEntity<?>  getPastAppointments();
	public ResponseEntity<?>  getAppointmentsOfPatient(int patient_id);
	public ResponseEntity<?>  getPatientByAppointment(int id) throws IdNotFoundException;
	
}
