package com.graymatter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.graymatter.dto.AppointmentDto;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.AppointmentService;


@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {

	@Autowired
	AppointmentService service;
	
	@GetMapping("/appointments")
	public ResponseEntity<?> getAllAppointments(){
		return service.getAllAppointments();
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<?> getAppointmentById(@PathVariable("id") int id) throws IdNotFoundException {
		return service.getAppointmentById(id);
	}
	
	@PostMapping("/appointment")
	public ResponseEntity<?> addAppointment(@RequestBody AppointmentDto appointment) {

		return service.addAppointment(appointment);
	}
	
	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<?> deleteAppointmentById(@PathVariable("id") int id) throws IdNotFoundException {
		return service.deleteAppointmentById(id);
	}
	
	@PutMapping("/appointment/{id}")
	public ResponseEntity<?> updateAppointment(@PathVariable("id") int id,@RequestBody AppointmentDto appointment) throws IdNotFoundException {
		return service.updateAppointment(id, appointment);
	}
	
	@GetMapping("/appointment/test/{id}")
	public ResponseEntity<?> getAllTestOfAppointment(@PathVariable("id") int id) throws IdNotFoundException{
		return service.getAllTestOfAppointment(id);
	}

	@GetMapping("/appointment/testresult/{id}")
	public ResponseEntity<?> getTestResultOfAppointment(@PathVariable("id") int id) throws IdNotFoundException{
		return service.getTestResultOfAppointment(id);
	}
	
	@GetMapping("/appointment/upcoming")
	public ResponseEntity<?> getUpcomingAppointments(){
		return service.getUpcomingAppointments();
		
	}
	
	@GetMapping("/appointment/past")
	public ResponseEntity<?> getPastAppointments(){
		return service.getPastAppointments();
	}
	
	@GetMapping("/appointment/patient/{patient_id}")
	public ResponseEntity<?> getAppointmentsOfPatient(@PathVariable("patient_id") int  patient_id){
		return service.getAppointmentsOfPatient(patient_id);
	}
	
	@GetMapping("/patinet/appointment/id")
	public ResponseEntity<?> getPatientByAppointment(@PathVariable("id") int id) throws IdNotFoundException {
		return service.getPatientByAppointment(id);
	}
	
	@GetMapping("/appointment/userId/{id}")
	public ResponseEntity<?> getAllAppointmentOfUser(@PathVariable("id") int id){
		return service.getAllAppointmentsOfUser(id);

	}
	@GetMapping("/appointment/center/{center_id}")
	public ResponseEntity<?> getAllAppointmentOfCenter(@PathVariable("center_id") int center_id) throws IdNotFoundException{
		return service.getAllAppointmentsOfCenter(center_id);

	}
	
	
	
}
