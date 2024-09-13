package com.graymatter.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.TestResultDao;

import com.graymatter.dto.PatientMapper;
import com.graymatter.dto.TestResultDto;
import com.graymatter.dto.TestResultMapper;
import com.graymatter.exceptions.IdNotFoundException;

@Service
public class TestResultService implements TestResultServiceInterface{

	@Autowired
	TestResultDao dao;
	
	@Autowired
	TestResultMapper mapper;
	
	@Autowired
	PatientMapper patientMapper;

	@Override
	public ResponseEntity<?> getAllTestResults() {
		// TODO Auto-generated method stub
		List<TestResultDto> testResults= dao.getAllTestResults().stream().map((resutlt)->mapper.mapToTestResultDto(resutlt)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!testResults.isEmpty()) {
			map.put("status",10);
			map.put("data", testResults);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No Test Results to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
	}

	@Override
	public ResponseEntity<?> addTestResult(TestResultDto testResult) {
		// TODO Auto-generated method stub
		TestResultDto test= mapper.mapToTestResultDto(dao.addTestResult(mapper.mapToTestResult(testResult)));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", test);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateTestResult(int id, TestResultDto testResult) throws IdNotFoundException {
		// TODO Auto-generated method stub
		TestResultDto test= mapper.mapToTestResultDto(dao.updateTestResult(id,mapper.mapToTestResult(testResult)));
		 Map<String, Object> map=new HashMap<>();
			map.put("status",10);
			map.put("data", test);
			return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getTestResultById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		TestResultDto test= mapper.mapToTestResultDto(dao.getTestResultById(id));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", test);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteTestResultById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
	TestResultDto testResult=mapper.mapToTestResultDto(dao.deleteTestResultById(id));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data",testResult);
		map.put("message", "Successfully Deleted");
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> viewTestResultByPatient(int patient_id) {
		// TODO Auto-generated method stub
		List<TestResultDto> testResults= dao.viewTestResultByPatient(patient_id).stream().map((result)->mapper.mapToTestResultDto(result)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!testResults.isEmpty()) {
			map.put("status",10);
			map.put("data", testResults);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No Test Results to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
	}
	
}
