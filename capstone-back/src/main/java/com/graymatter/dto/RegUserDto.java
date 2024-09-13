package com.graymatter.dto;


import com.graymatter.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUserDto {
	private String username;
	private String email;
	private String password;
	private Role role;

}