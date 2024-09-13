package com.graymatter.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graymatter.dto.UserDto;
import com.graymatter.exceptions.EmailNotFoundException;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping("/user/username/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable("username") String userName){
		return service.getUserByUserName(userName);
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers(){
		return service.getAllUsers();
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserByUserId(@PathVariable("id") int id) throws IdNotFoundException{
		return service.getUserById(id);
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) throws IdNotFoundException{
		return service.deleteUser(id);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UserDto user) throws IdNotFoundException{
		return service.updateUser(id,user);
	}
	@PostMapping("/forgot/{email}/{password}")
	public ResponseEntity<?> forgotPassword(@PathVariable("email") String email,@PathVariable("password") String password) throws EmailNotFoundException {
		return service.forgotPassword(email, password);
	}
	@PutMapping("/user/{email}/{currentPassword}/{newPassword}")
	public ResponseEntity<?> resetPassword(@PathVariable("email") String email,@PathVariable("currentPassword") String currentPassword,@PathVariable("newPassword") String newPassword) throws EmailNotFoundException {
		return service.resetPassword(email, currentPassword, newPassword);
	}

}
