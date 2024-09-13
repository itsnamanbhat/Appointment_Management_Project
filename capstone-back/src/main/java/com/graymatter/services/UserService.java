package com.graymatter.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graymatter.dao.UserDao;
import com.graymatter.dto.UserDto;
import com.graymatter.dto.UserMapper;
import com.graymatter.entities.User;
import com.graymatter.exceptions.EmailNotFoundException;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.exceptions.InvalidCredentialsException;

@Service
public class UserService implements UserServiceInterface{
	@Autowired
	UserDao dao;
	
	@Autowired
	UserMapper mapper;
	
	@Override
	public ResponseEntity<?> getAllUsers() {
		List<User> userList= dao.getAllUsers();
		List<UserDto> output=userList.stream().map((user)->mapper.mapToUserDto(user)).collect(Collectors.toList());
		Map<String, Object> map=new HashMap<>();
		if(!output.isEmpty()) {
			map.put("status",10);
			map.put("data", output);
			return new ResponseEntity<>(map,HttpStatus.OK);
		}else {
			map.put("status",20);
			map.put("data", "No Appointments to display");
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			
		}
			
	}

//	@Override
//	public ResponseEntity<?> addNewUser(UserDto user) throws UserOrEmailAlreadyPresent {
//		UserDto output= mapper.mapToUserDto(dao.addNewUser(mapper.mapToUser(user)));
//		Map<String, Object> map=new HashMap<>();
//		map.put("status",10);
//		map.put("data", output);
//		map.put("message", "user added successfully");
//		return new ResponseEntity<>(map,HttpStatus.OK);
//	}

	@Override
	public ResponseEntity<?> updateUser(int userId,UserDto user) throws IdNotFoundException {
		UserDto output=mapper.mapToUserDto( dao.updateUser(userId, mapper.mapToUser(user)));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("message", "user with userId "+userId+" updated successfully");
		map.put("data", output);
		return new ResponseEntity<>(map,HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> deleteUser(int userId) throws IdNotFoundException {
		UserDto output=mapper.mapToUserDto(dao.deleteUser(userId));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", output);
		map.put("message", "user with userId "+userId+" deleted successfully");
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> getUserById(int userId) throws IdNotFoundException {
		UserDto output= mapper.mapToUserDto(dao.getUserById(userId));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", output);
		map.put("message", "user with userId "+userId+"  fetched successfully");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserByUserName(String username) {
		UserDto output= mapper.mapToUserDto(dao.findUserByUsername(username));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", output);
		map.put("message", "user with username "+username+"  fetched successfully");
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> loginUser(String username, String passoword) throws InvalidCredentialsException {
		UserDto output= mapper.mapToUserDto(dao.login(username, passoword));
		Map<String, Object> map=new HashMap<>();
		map.put("status",10);
		map.put("data", output);
		map.put("message", "user with username "+username+"  successfully loggedIn");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> forgotPassword(String email, String password) throws EmailNotFoundException {
		User updatedUser= dao.forgotPassword(email, password);
		UserDto output= mapper.mapToUserDto(updatedUser);
		Map<String, Object> map=new HashMap<>();
		map.put("status",200);
		map.put("data", output);
		map.put("message", "password changed for the email "+ email);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> resetPassword(String email, String currentPassword, String newPassword)
			throws EmailNotFoundException {
		User updatedUser= dao.resetPassword(email,currentPassword,newPassword);
		UserDto output= mapper.mapToUserDto(updatedUser);
		Map<String, Object> map=new HashMap<>();
		map.put("status",200);
		map.put("data", output);
		map.put("message", "password reset for the email "+ email +" done successfully");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
