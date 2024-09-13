package com.graymatter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.graymatter.entities.ContactUs;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.repositories.ContactusRepository;

@Repository
public class ContactusDao {

	@Autowired
	ContactusRepository repo;
	
	public ContactUs addMessage(ContactUs c) {
		return repo.save(c);
	}
	
	public ContactUs deleteMessage(int id) throws IdNotFoundException {
		ContactUs message= repo.findById(id).orElseThrow(()->new IdNotFoundException("no id found"));
		 repo.deleteById(null);
		 return message;
	}
}
