package com.graymatter.configurations;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private AuthenticationProvider authProvider;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain doSecurityFilterChain(HttpSecurity http) throws Exception {
		http
		.cors().configurationSource(corsConfigurationSource())
		.and()
		.csrf().disable()
		.authorizeRequests()
        .requestMatchers("/api/v1/appointment/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/centerAdministrator/**").hasAnyRole("CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/diagnosticcenter/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/diagnostictest/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/patient/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/user/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/testresult/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/booking/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/request/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/approve/**").hasAnyRole("USER","CENTER_ADMIN","ADMIN")
        .requestMatchers("/api/v1/contactcus").permitAll()
       .requestMatchers("/api/v1/auth/**").permitAll()
       .requestMatchers("/api/v1/forgot/**").permitAll()
        .requestMatchers("/api/v1/**").hasRole("ADMIN")
        .anyRequest().permitAll()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}	
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
  
}
