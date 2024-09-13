package com.graymatter.services;



import org.springframework.http.ResponseEntity;

import com.graymatter.dto.UserDto;
import com.graymatter.exceptions.EmailNotFoundException;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.exceptions.InvalidCredentialsException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;

public interface UserServiceInterface {
	public ResponseEntity<?> getAllUsers();
	public ResponseEntity<?> updateUser(int userId,UserDto user) throws IdNotFoundException;
	public ResponseEntity<?> deleteUser(int userId) throws IdNotFoundException;
	public ResponseEntity<?> getUserById(int userId) throws IdNotFoundException;
	public ResponseEntity<?> getUserByUserName(String username);
	public ResponseEntity<?> loginUser(String username,String passoword) throws InvalidCredentialsException;
	public ResponseEntity<?> forgotPassword(String email, String password) throws EmailNotFoundException;
	public ResponseEntity<?> resetPassword(String email, String currentPassword,String newPassword) throws EmailNotFoundException;
}
