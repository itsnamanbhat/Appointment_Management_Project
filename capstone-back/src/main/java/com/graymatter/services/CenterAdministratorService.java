package com.graymatter.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.CenterAdministatorDao;
import com.graymatter.dto.CenterAdministratorDto;
import com.graymatter.dto.CenterAdministratorMapper;
import com.graymatter.entities.CenterAdministrator;
import com.graymatter.exceptions.IdNotFoundException;

@Service
public class CenterAdministratorService implements CenterAdministratorServiceInterface{

	@Autowired
	CenterAdministatorDao dao;
	
	@Autowired
	CenterAdministratorMapper mapper;
	
	@Override
	public ResponseEntity<?> addCenterAdministrator(CenterAdministratorDto centerAdministrator) {
		CenterAdministratorDto output= mapper.mapToCenterAdministratorDto(dao.addCenterAdministrator(mapper.mapToCenterAdministrator(centerAdministrator)));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "CenterAdministrator added successfully");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllCenterAdiministrators() {
		List<CenterAdministrator> calist= dao.getAllCenterAdministrators();
		List<CenterAdministratorDto> output= calist.stream().map((cAdmin)->mapper.mapToCenterAdministratorDto(cAdmin)).collect(Collectors.toList());
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "CenterAdministrators fetched successfully");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getCenterAdministratorById(int centerAdministratorId) throws IdNotFoundException {
		CenterAdministratorDto output= mapper.mapToCenterAdministratorDto(dao.getCenterAdministratorById(centerAdministratorId));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "CenterAdministrator fetched successfully");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> deleteCenterAdministrator(int centerAdminId) throws IdNotFoundException {
		CenterAdministrator output=dao.deleteCenterAdministrator(centerAdminId);
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "CenterAdmin deleted successfully");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		
	}

	@Override
	public ResponseEntity<?> updateCenterAdministrator(int id,CenterAdministratorDto centerAdministrator) {
		CenterAdministratorDto output= mapper.mapToCenterAdministratorDto(dao.updateCenterAdministrator(id, mapper.mapToCenterAdministrator(centerAdministrator)));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "CenterAdministrator updated successfully");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getCenterAdministratorByUserId(int userId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		CenterAdministratorDto output= mapper.mapToCenterAdministratorDto(dao.getCenterAdministratorByUserId(userId));
		Map<String,Object> map= new HashMap<>();
		map.put("status", 10);
		map.put("data",output);
		map.put("message", "CenterAdministrator fetched successfully");
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}

}
