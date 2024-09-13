package com.graymatter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.graymatter.dao.AuthenticationDao;
import com.graymatter.dto.LoginUserDto;
import com.graymatter.dto.RegUserDto;
import com.graymatter.entities.User;
import com.graymatter.exceptions.EmailNotFoundException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationDao authenticationDao;
	
	@Autowired
	private AuthenticationManager authManager;
	
	public User signUp(RegUserDto regUserDto)throws UserOrEmailAlreadyPresent {
		return authenticationDao.signUp(regUserDto);
	}

	public User login(LoginUserDto loginUserDto){
			authManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginUserDto.getEmail(),
					loginUserDto.getPassword()
					)
				);
//		}
		
		return authenticationDao.login(loginUserDto);
	}

}