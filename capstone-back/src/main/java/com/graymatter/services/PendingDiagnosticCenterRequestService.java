package com.graymatter.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.PendingDiagnosticCenterRequestDao;
import com.graymatter.dto.PendingDiagnosticCenterRequestDto;
import com.graymatter.dto.PendingDiagnosticCenterRequestDtoMapper;
import com.graymatter.entities.PendingDiagnosticCenterRequest;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;

@Service
public class PendingDiagnosticCenterRequestService implements PendingDiagnosticCenterRequestServiceInterface{

	@Autowired
	PendingDiagnosticCenterRequestDao dao;
	
	
	@Autowired
	PendingDiagnosticCenterRequestDtoMapper mapper;
	
	@Override
	public ResponseEntity<?> requestNewDiagnosticCenter(PendingDiagnosticCenterRequestDto request) throws UserOrEmailAlreadyPresent {
		PendingDiagnosticCenterRequestDto newRequest=mapper.mapToPendingDiagnosticCenterRequestDto( dao.requestNewDiagnosticCenter(mapper.mapToPendingDiagnosticCenterRequest(request)));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", newRequest);
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> approveRequest(int id) throws IdNotFoundException {
		PendingDiagnosticCenterRequestDto newRequest=mapper.mapToPendingDiagnosticCenterRequestDto( dao.approveRequest(id));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", newRequest);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getPendingRequests() {
		List<PendingDiagnosticCenterRequest> daoList=dao.getPendingRequests();

		List<PendingDiagnosticCenterRequestDto> output= daoList.stream().map((a)->mapper.mapToPendingDiagnosticCenterRequestDto(a)).collect(Collectors.toList());	Map<String, Object> map=new HashMap<>();
			if(!output.isEmpty()) {
				map.put("status",10);
				map.put("data", output);
				return new ResponseEntity<>(map,HttpStatus.OK);
				
			}else {		
				map.put("status",20);
			map.put("data", "No pendingRequests to display");
			}
				return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
				
			}

	@Override
	public ResponseEntity<?> getAllRequests() {
		List<PendingDiagnosticCenterRequest> daoList=dao.getAllRequests();

		List<PendingDiagnosticCenterRequestDto> output= daoList.stream().map((a)->mapper.mapToPendingDiagnosticCenterRequestDto(a)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
			if(!output.isEmpty()) {
				map.put("status",10);
				map.put("data", output);
				return new ResponseEntity<>(map,HttpStatus.OK);
				
			}else {		
				map.put("status",20);
			map.put("data", "No Requests to display");
			}
				return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> deleteRequest(int id) throws IdNotFoundException {
		PendingDiagnosticCenterRequestDto request=mapper.mapToPendingDiagnosticCenterRequestDto( dao.deleteRequest(id));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", request);
		map.put("message","Approval request has been denied");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	}
	

