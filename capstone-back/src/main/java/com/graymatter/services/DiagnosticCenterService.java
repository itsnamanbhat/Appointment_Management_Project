package com.graymatter.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.DiagnosticCenterDao;
import com.graymatter.dao.TestResultDao;
import com.graymatter.dto.DiagnosticCenterDto;
import com.graymatter.dto.DiagnosticCenterMapper;
import com.graymatter.dto.DiagnosticTestDto;
import com.graymatter.dto.TestResultDto;
import com.graymatter.dto.TestResultMapper;
import com.graymatter.entities.Appointment;
import com.graymatter.entities.DiagnosticCenter;
import com.graymatter.entities.DiagnosticTest;
import com.graymatter.exceptions.IdNotFoundException;

@Service
public class DiagnosticCenterService implements DiagnsoticCenterServiceInterface{
	
	@Autowired
	DiagnosticCenterDao dao;
	
	@Autowired
	DiagnosticCenterMapper mapper;
	
	@Autowired
	TestResultMapper testResultMapper;
	
	@Autowired
	TestResultDao testResultDao;
	
	@Override
	public ResponseEntity<?> getAllDiagnosticCenters() {
		List<DiagnosticCenter> dclist= dao.getAllDiagnosticCenters();
		List<DiagnosticCenterDto> output= dclist.stream().map((dc)->mapper.mapToDiagnosticCenterDto(dc)).collect(Collectors.toList());
		Map<String,Object> map= new HashMap<>();
		if(!output.isEmpty()) {
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "DiagnosticCenters fetched successfully");
			return new ResponseEntity<>(map,HttpStatus.OK);
	}else {
		map.put("status",20);
		map.put("data", "No DiagnosticCenters to display");
		return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
		
	}
	}

	@Override
	public ResponseEntity<?> addDiagnosticCenter(DiagnosticCenterDto diagnosticCenter) {
		DiagnosticCenterDto output= mapper.mapToDiagnosticCenterDto(dao.addDiagnosticCenter(mapper.mapToDiagnosticCenter(diagnosticCenter)));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "DiagnosticCenter added successfully");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getDiagnosticCenterById(int diagnosticCenterId) throws IdNotFoundException {
		DiagnosticCenterDto output= mapper.mapToDiagnosticCenterDto(dao.getDiagnosticCenterById(diagnosticCenterId));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "DiagnosticCenter fetched successfully");
			return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	
	@Override
	public ResponseEntity<?> removeDiagnosticCenter(int diagnosticCentreId) throws IdNotFoundException {
		String output=dao.deleteDiagnosticCenter(diagnosticCentreId);
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
		 map.put("data", output);
			map.put("message", "DiagnosticCenter deleted successfully");
			return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	@Override
	public ResponseEntity<?> updateDiagnosticCenter(int id, DiagnosticCenterDto diagnosticCenter) throws IdNotFoundException {
		DiagnosticCenterDto output= mapper.mapToDiagnosticCenterDto(dao.updateDiagnosticCenter(id,mapper.mapToDiagnosticCenter(diagnosticCenter)));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "DiagnosticCenter updated successfully");
			return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> viewTestResults(int diagnosticCentreId, String testName) {
//		DiagnosticCenterDto center= mapper.mapToDiagnosticCenterDto(dao.getDiagnosticCenterById(diagnosticCentreId));
//		TestResultDto testResult=testResultDao.getTestResultByName(testName);
		return null;
		

	}

	@Override
	public ResponseEntity<?> addTest(int diagnosticCentreId, int testId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public ResponseEntity<?> getListOfAppointments(String centerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> findByDiagnosticTests(List<String> diagnosticTestNames) {
		// TODO Auto-generated method stub
		List<DiagnosticCenter> dcList= dao.findByDiagnosticTests(diagnosticTestNames);
		List<DiagnosticCenterDto> output= dcList.stream().map((dc)->mapper.mapToDiagnosticCenterDto(dc)).collect(Collectors.toList());
		Map<String,Object> map= new HashMap<>();
		if(!output.isEmpty()) {
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "DiagnosticCenters fetched successfully");
			return new ResponseEntity<>(map,HttpStatus.OK);
	}else {
		map.put("status",20);
		map.put("data", "No DiagnosticCenters to display");
		return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
		
	}
	}

	public ResponseEntity<?> getDiagnosticCenterByUserId(int id) {
		DiagnosticCenterDto output= mapper.mapToDiagnosticCenterDto(dao.getDiagnosticCenterByUserId(id));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "DiagnosticCenter fetched successfully");
			return new ResponseEntity<>(map,HttpStatus.OK);
	}

}
