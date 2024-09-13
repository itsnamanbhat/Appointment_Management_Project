package com.graymatter.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graymatter.configurations.UserPrincipal;
import com.graymatter.dto.LoginResponse;
import com.graymatter.dto.LoginUserDto;
import com.graymatter.dto.RegUserDto;
import com.graymatter.entities.User;
import com.graymatter.exceptions.EmailNotFoundException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;
import com.graymatter.repositories.UserRepository;
import com.graymatter.services.AuthenticationService;
import com.graymatter.services.JwtService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody RegUserDto regUserDto) throws UserOrEmailAlreadyPresent{
		User regUser = authService.signUp(regUserDto);
		return ResponseEntity.ok(regUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto){
		if (!userRepository.existsByEmail(loginUserDto.getEmail())) {
			return ResponseEntity.ok(null);
        }
		User authenticatedUser = authService.login(loginUserDto);
		
		String token = jwtService.generateToken(new UserPrincipal(authenticatedUser));
		
		LoginResponse loginResponse=new LoginResponse();
		loginResponse.setToken(token);
		loginResponse.setExpirationTime(jwtService.expirationTime());
		loginResponse.setUser(authenticatedUser);
		return ResponseEntity.ok(loginResponse);
		
	}
	
	@PostMapping("/forgot-password/{email}")
	public ResponseEntity<?> checkIfEmailExists(@PathVariable String email)throws EmailNotFoundException{
		System.out.println(email);
		User u= userRepository.findByEmail(email).orElseThrow(()->new EmailNotFoundException("Email Id does not exist"));
		 return ResponseEntity.ok(u);
	}
}