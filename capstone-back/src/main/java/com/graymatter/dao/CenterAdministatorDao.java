package com.graymatter.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.graymatter.entities.CenterAdministrator;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.repositories.CenterAdministratorRepository;
@Repository
public class CenterAdministatorDao {

	@Autowired
	CenterAdministratorRepository repo;

	public CenterAdministrator addCenterAdministrator(CenterAdministrator centerAdministrator) {
		return repo.save(centerAdministrator);
	}

	public List<CenterAdministrator> getAllCenterAdministrators() {
		return repo.findAll();
	}

	public CenterAdministrator getCenterAdministratorById(int centerAdministratorId) throws IdNotFoundException {
		return repo.findById(centerAdministratorId).orElseThrow(()->new IdNotFoundException("center Administrator id: "+centerAdministratorId+" is not present"));
	}

	public CenterAdministrator deleteCenterAdministrator(int centerAdminId) throws IdNotFoundException {
		CenterAdministrator ca= repo.findById(centerAdminId).orElseThrow(()->new IdNotFoundException("center Administrator id: "+centerAdminId+" is not present"));
		repo.deleteById(centerAdminId);
		return ca;
		
	}

	public CenterAdministrator updateCenterAdministrator(int id,CenterAdministrator centerAdministrator) {

        CenterAdministrator existingCenterAdministrator = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("CenterAdministrator not found with id: " +id));

//        if (centerAdministrator.getName() != null) {
//            existingCenterAdministrator.setName(centerAdministrator.getName());
//        }
//        if (centerAdministrator.getPhoneNo() != null) {
//            existingCenterAdministrator.setPhoneNo(centerAdministrator.getPhoneNo());
//        }
//        if (centerAdministrator.getAddress() != null) {
//            existingCenterAdministrator.setAddress(centerAdministrator.getAddress());
//        }
//        if (centerAdministrator.getDiagnosticCenter() != null) {
//            existingCenterAdministrator.setDiagnosticCenter(centerAdministrator.getDiagnosticCenter());
//        }
//        if (centerAdministrator.getUser() != null) {
//            existingCenterAdministrator.setUser(centerAdministrator.getUser());
//        }

        return repo.save(existingCenterAdministrator);
    }

	public CenterAdministrator getCenterAdministratorByUserId(int userId)  {
		
		 return null;
				
	}
	}

