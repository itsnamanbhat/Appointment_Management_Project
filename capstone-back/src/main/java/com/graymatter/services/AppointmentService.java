package com.graymatter.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.AppointmentDao;
import com.graymatter.dto.AppointmentDto;
import com.graymatter.dto.AppointmentMapper;
import com.graymatter.dto.DiagnosticTestDto;
import com.graymatter.dto.DiagnosticTestMapper;
import com.graymatter.dto.PatientDto;
import com.graymatter.dto.PatientMapper;
import com.graymatter.dto.TestResultDto;
import com.graymatter.dto.TestResultMapper;
import com.graymatter.entities.Appointment;
import com.graymatter.entities.DiagnosticTest;
import com.graymatter.entities.TestResult;
import com.graymatter.exceptions.IdNotFoundException;

@Service
public class AppointmentService implements AppointmentServiceInterface{

	@Autowired
	AppointmentDao dao;
	
	@Autowired
	AppointmentMapper mapper;
	
	@Autowired
	DiagnosticTestMapper testMapper;
	
	@Autowired
	TestResultMapper resultMapper;
	
	@Autowired
	PatientMapper patientMapper;
	
	
	@Override
	public ResponseEntity<?> getAllAppointments() {
		// TODO Auto-generated method stub
		List<Appointment> aList=dao.getAllAppointments();

	List<AppointmentDto> appointmentDtoList= aList.stream().map((a)->mapper.mapToAppointmentDto(a)).collect(Collectors.toList());	Map<String, Object> map=new HashMap<>();
		if(!appointmentDtoList.isEmpty()) {
			map.put("status",10);
			map.put("data", appointmentDtoList);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {		
			map.put("status",20);
		map.put("data", "No Appointments to display");
		}
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}

	@Override
	public ResponseEntity<?> getAppointmentById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		AppointmentDto appointment= mapper.mapToAppointmentDto(dao.getAppointmentById(id));
		appointment.getDiagnosticCenter().getName();
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", appointment);
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> addAppointment(AppointmentDto appointment) {
		// TODO Auto-generated method stub
		AppointmentDto appointmentDto= mapper.mapToAppointmentDto(dao.addAppointment(mapper.mapToAppointment(appointment))) ;
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", appointmentDto);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteAppointmentById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		AppointmentDto appointmentDto= mapper.mapToAppointmentDto(dao.deleteAppointmentById(id));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", appointmentDto);
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> updateAppointment(int id, AppointmentDto appointment) throws IdNotFoundException {
		AppointmentDto appointmentDto= mapper.mapToAppointmentDto(dao.updateAppointment(id,mapper.mapToAppointment(appointment))) ;
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", appointmentDto);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllTestOfAppointment(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		List<DiagnosticTest> testSet=dao.getAllTestOfAppointment(id);
		List<DiagnosticTestDto> tests= testSet.stream().map((test)->testMapper.mapToDiagnosticTestDto(test)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!tests.isEmpty()) {
			map.put("status",10);
			map.put("data", tests);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No tests to display");
			return new ResponseEntity<>(map,HttpStatus.NO_CONTENT);
			
		}
	}

	@Override
	public ResponseEntity<?> getPatientByAppointment(int id) throws IdNotFoundException{
		// TODO Auto-generated method stub
		PatientDto patient= patientMapper.mapToPatientDto(dao.getPatientByAppointment(id)) ;
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", patient);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

//	@Override
//	public DiagnosticCenter getDiagnosticCenterOfAppointment(int id) {
//		// TODO Auto-generated method stub
//		return dao.getDiagnosticCenterOfAppointment(id);
//	}

	@Override
	public ResponseEntity<?> getTestResultOfAppointment(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		List<TestResult> resultSet=dao.getTestResultOfAppointment(id);
		List<TestResultDto> testResults= resultSet.stream().map((result)->resultMapper.mapToTestResultDto(result)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!testResults.isEmpty()) {
			map.put("status",10);
			map.put("data", testResults);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No test results to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
		
	}

	@Override
	public ResponseEntity<?> getUpcomingAppointments() {
		// TODO Auto-generated method stub
		List<Appointment> aList=dao.getUpcomingAppointments();
		List<AppointmentDto> appointments =aList.stream().map((a)->mapper.mapToAppointmentDto(a)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!appointments.isEmpty()) {
			map.put("status",10);
			map.put("data", appointments);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No appointments to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
	}

	@Override
	public ResponseEntity<?> getPastAppointments() {
		// TODO Auto-generated method stub
		List<Appointment> aList=dao.getPastAppointments();
		List<AppointmentDto> appointments= aList.stream().map((a)->mapper.mapToAppointmentDto(a)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!appointments.isEmpty()) {
			map.put("status",10);
			map.put("data", appointments);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No appointments to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
	}


	@Override
	public ResponseEntity<?> getAppointmentsOfPatient(int patient_id) {
		// TODO Auto-generated method stub
		List<Appointment> aList=dao.getAppointmentsOfPatient(patient_id);
		List<AppointmentDto> appointments= aList.stream().map((a)->mapper.mapToAppointmentDto(a)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!appointments.isEmpty()) {
			map.put("status",10);
			map.put("data", appointments);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No appointments to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
		
	}

	public ResponseEntity<?> getAllAppointmentsOfUser(int id) {
		// TODO Auto-generated method stub
		List<Appointment> aList=dao.getAllAppointmentsOfUser(id);
		List<AppointmentDto> appointments= aList.stream().map((a)->mapper.mapToAppointmentDto(a)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!appointments.isEmpty()) {
			map.put("status",10);
			map.put("data", appointments);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No appointments to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
		
	}

	public ResponseEntity<?> getAllAppointmentsOfCenter(int center_id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		List<Appointment> aList=dao.getAllAppointmentsOfCenter(center_id);
		List<AppointmentDto> appointments= aList.stream().map((a)->mapper.mapToAppointmentDto(a)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!appointments.isEmpty()) {
			map.put("status",10);
			map.put("data", appointments);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No appointments to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
		
	
	}

}
