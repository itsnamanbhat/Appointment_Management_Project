package com.graymatter.configurations;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//import com.graymatter.exceptions.HadlerException;
import com.graymatter.services.JwtService;
import com.graymatter.services.MyUserDeatilsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//	@Autowired
//	private HadlerException handlerException;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MyUserDeatilsService myUserDatilsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		if(authHeader==null || !authHeader.startsWith("Bearer ")) { // should go for login or signUp
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authHeader.substring(7);
		String email = jwtService.extractUserName(token);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(email!=null && authentication==null) {
			UserDetails userDeatils = this.myUserDatilsService.loadUserByUsername(email);
			
			if(jwtService.isTokenValid(token, userDeatils)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDeatils,
						null,
						userDeatils.getAuthorities()
					);
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
