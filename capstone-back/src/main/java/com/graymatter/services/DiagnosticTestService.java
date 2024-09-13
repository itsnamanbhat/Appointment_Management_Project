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

import com.graymatter.dao.DiagnosticTestDao;
import com.graymatter.dto.DiagnosticTestDto;
import com.graymatter.dto.DiagnosticTestMapper;
import com.graymatter.entities.DiagnosticTest;
import com.graymatter.exceptions.IdNotFoundException;

@Service
public class DiagnosticTestService implements DiagnosticTestServiceInterface{

	@Autowired
	 DiagnosticTestDao dao;
	
	@Autowired
	DiagnosticTestMapper mapper;
	
	@Override
	public ResponseEntity<?> getAllDiagnosticTest() {
		// TODO Auto-generated method stub
		List<DiagnosticTest> testList=dao.getAllDiagnosticTest();
		List<DiagnosticTestDto> tests= testList.stream().map((test)->mapper.mapToDiagnosticTestDto(test)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!tests.isEmpty()) {
			map.put("status",10);
			map.put("data", tests);
			map.put("message", "Successfully Retrived Dignostic Test");
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No Tests to display");
			
			return new ResponseEntity<>(map,HttpStatus.NO_CONTENT);
			
		}
	}

	@Override
	public ResponseEntity<?> addNewDiagnosticTest(DiagnosticTestDto diagnosticTest) {
		// TODO Auto-generated method stub
		DiagnosticTestDto test= mapper.mapToDiagnosticTestDto(dao.addNewDiagnosticTest(mapper.mapToDiagnosticTest(diagnosticTest)));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", test);
		map.put("message", "Successfully Added Dignostic Test");

	   return new ResponseEntity<>(map,HttpStatus.OK);
			
		
	}

	@Override
	public ResponseEntity<?> updateDiagnosticTestDetails(int id, DiagnosticTestDto diagnosticTest) throws IdNotFoundException {
		// TODO Auto-generated method stub
		DiagnosticTestDto test= mapper.mapToDiagnosticTestDto(dao.updateDiagnosticTestDetails(id, mapper.mapToDiagnosticTest(diagnosticTest)));
		
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", test);
		map.put("message", "Successfully Updated Dignostic Test");

	   return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getDiagnosticTestById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		DiagnosticTestDto test= mapper.mapToDiagnosticTestDto(dao.getDiagnosticTestById(id));
		
				Map<String, Object> map=new HashMap<>();
				map.put("status",10);
				map.put("data", test);
			   return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteDiagnosticTestById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		DiagnosticTestDto diagnosticTest=mapper.mapToDiagnosticTestDto(dao.deleteDiagnosticTestById(id));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data",diagnosticTest);
		map.put("message", "Successfully Deleted Diagnostic Test");
	   return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	@Override
	public ResponseEntity<?> getAllDiagnosticTestDto() {
		// TODO Auto-generated method stub
		List<DiagnosticTestDto> tests= dao.getAllDiagnosticTest().stream().map((test)->mapper.mapToDiagnosticTestDto(test)).collect(Collectors.toList());		Map<String, Object> map=new HashMap<>();
		if(!tests.isEmpty()) {
			map.put("status",10);
			map.put("data", tests);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No Tests to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
	}

	public ResponseEntity<?> getTestsOfCenter(int id) {
		// TODO Auto-generated method stub
		List<DiagnosticTestDto> tests= dao.getTestsOfCenter(id).stream().map((test)->mapper.mapToDiagnosticTestDto(test)).collect(Collectors.toList());		Map<String, Object> map=new HashMap<>();
		if(!tests.isEmpty()) {
			map.put("status",10);
			map.put("data", tests);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("status",20);
			map.put("data", "No Tests to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
	}
	

}
