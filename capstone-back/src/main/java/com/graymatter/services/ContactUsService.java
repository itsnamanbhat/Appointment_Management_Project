package com.graymatter.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.ContactusDao;
import com.graymatter.dto.ContactUsDto;
import com.graymatter.dto.ContactUsMapper;
import com.graymatter.exceptions.IdNotFoundException;

@Service
public class ContactUsService {
	@Autowired
	ContactusDao dao;
	
	@Autowired
	ContactUsMapper mapper;
	
	public ResponseEntity<?> addMessage(ContactUsDto c){
		ContactUsDto output= mapper.mapToContactUsDto(dao.addMessage(mapper.mapToContactUs(c)));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "Message added successfully");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	public ResponseEntity<?> deleteMessage (int id) throws IdNotFoundException{
		ContactUsDto output= mapper.mapToContactUsDto(dao.deleteMessage(id));
		Map<String,Object> map= new HashMap<>();
		 map.put("status", 10);
			map.put("data",output);
			map.put("message", "Message deleted successfully");
			return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
