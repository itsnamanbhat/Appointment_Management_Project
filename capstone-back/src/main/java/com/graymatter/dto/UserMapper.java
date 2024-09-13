package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.User;

@Component
public class UserMapper {
	public User mapToUser(UserDto userDto) {
		return new User(userDto.getId(),userDto.getUsername(),userDto.getPassword(),userDto.getEmail(),userDto.getRole());
	}
	public UserDto mapToUserDto(User u) {
		return new UserDto(u.getId(),u.getUsername(),u.getPassword(),u.getEmail(),u.getRole());
	}
}
