package com.graymatter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.graymatter.configurations.UserPrincipal;
import com.graymatter.entities.User;
import com.graymatter.repositories.UserRepository;

@Service
public class MyUserDeatilsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).get();
		System.out.println(user);
		if(user==null)
			throw new UsernameNotFoundException(username);

		return new UserPrincipal(user);
	}

}