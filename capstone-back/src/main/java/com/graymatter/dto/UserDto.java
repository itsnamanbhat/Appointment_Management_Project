package com.graymatter.dto;

import com.graymatter.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
	private int id;
	private String username;
	private String password;
	private String email;
	private Role role;
}