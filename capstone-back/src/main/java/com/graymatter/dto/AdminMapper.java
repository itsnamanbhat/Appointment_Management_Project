package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.Admin;
@Component
public class AdminMapper {
	public Admin mapToAdmin(AdminDto aDto) {
		return new Admin(aDto.getId(),aDto.getName(),aDto.getPhoneNo(),aDto.getAddress(),aDto.getUser());
	}
	
	public AdminDto mapToAdminDto(Admin admin) {
		return new AdminDto(admin.getId(),admin.getName(),admin.getPhoneNo(),admin.getAddress(),admin.getUser());
	}
}
