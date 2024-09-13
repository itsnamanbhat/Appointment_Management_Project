package com.graymatter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.graymatter.dto.AdminDto;
import com.graymatter.entities.Admin;
import com.graymatter.repositories.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	AdminRepository repo;

	public Admin findAll() {
		// TODO Auto-generated method stub
		return repo.findAll().get(0);
	}

	public Admin update(Admin admin) {
		// TODO Auto-generated method stub
		return repo.save(admin);
	}
	
}
