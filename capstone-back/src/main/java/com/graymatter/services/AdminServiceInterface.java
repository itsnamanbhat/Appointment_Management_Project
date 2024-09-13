package com.graymatter.services;

import org.springframework.http.ResponseEntity;

import com.graymatter.dto.AdminDto;

public interface AdminServiceInterface {
	public ResponseEntity<?> getAdmin();
	public ResponseEntity<?> updateAdmin(AdminDto admin);
	
	

}
