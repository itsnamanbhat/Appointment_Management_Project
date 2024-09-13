package com.graymatter.configurations;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.graymatter.entities.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {
	
	@Autowired
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
	}

	@Override
	public String getPassword() {
		System.out.println(user.getRole()+((Object)user.getRole()).getClass().getSimpleName());
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
}