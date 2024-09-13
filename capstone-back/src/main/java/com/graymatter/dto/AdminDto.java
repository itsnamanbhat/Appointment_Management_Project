package com.graymatter.dto;

import com.graymatter.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDto {
	private int id;
	private String name;
	private String phoneNo;
	private String address;
	private User user;
}
