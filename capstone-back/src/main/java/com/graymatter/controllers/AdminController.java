package com.graymatter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graymatter.dto.AdminDto;
import com.graymatter.services.AdminService;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@GetMapping("/admin")
		public ResponseEntity<?> getAdmin(){
			return service.getAdmin();
		}
	
	
	@PutMapping("/admin/{id}")
		public ResponseEntity<?> updateAdmin(@PathVariable("id") int id,@RequestBody AdminDto dto){
			return service.updateAdmin(dto);
		}
	

}
