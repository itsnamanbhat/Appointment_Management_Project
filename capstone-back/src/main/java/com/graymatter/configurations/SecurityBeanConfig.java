package com.graymatter.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.graymatter.services.MyUserDeatilsService;

@Configuration
public class SecurityBeanConfig {
	
	@Autowired
	private MyUserDeatilsService service;

	@Bean
	public PasswordEncoder getPasswordEncder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new MyUserDeatilsService();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(service);
		dao.setPasswordEncoder(new BCryptPasswordEncoder());
		return dao;
	}
}