package com.graymatter.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.graymatter.dto.CenterAdministratorDto;
import com.graymatter.exceptions.IdNotFoundException;

public interface CenterAdministratorServiceInterface {
	public ResponseEntity<?> addCenterAdministrator(CenterAdministratorDto centerAdministrator);
	public ResponseEntity<?> getAllCenterAdiministrators();
	public ResponseEntity<?> getCenterAdministratorById(int centerAdministratorId) throws IdNotFoundException;
	public ResponseEntity<?> deleteCenterAdministrator(int centerAdminId) throws IdNotFoundException;
	public ResponseEntity<?> updateCenterAdministrator(int id,CenterAdministratorDto centerAdministrator);
	public ResponseEntity<?> getCenterAdministratorByUserId(int userId) throws IdNotFoundException;
}
