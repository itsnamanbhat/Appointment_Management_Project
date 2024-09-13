package com.graymatter.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.AdminDao;
import com.graymatter.dto.AdminDto;
import com.graymatter.dto.AdminMapper;
import com.graymatter.dto.UserDto;
import com.graymatter.repositories.AdminRepository;

@Service
public class AdminService implements AdminServiceInterface{

	@Autowired
	AdminDao dao;
	
	@Autowired
	AdminMapper mapper;
	
	@Override
	public ResponseEntity<?> getAdmin() {
		// TODO Auto-generated method stub
		AdminDto output= mapper.mapToAdminDto(dao.findAll());
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("message", "Admin fetched successfully");
		map.put("data", output);
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> updateAdmin(AdminDto admin) {
		// TODO Auto-generated method stub
		
		AdminDto output=mapper.mapToAdminDto(dao.update(mapper.mapToAdmin(admin)));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("message", "Admin updated successfully");
		map.put("data", output);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	

}
