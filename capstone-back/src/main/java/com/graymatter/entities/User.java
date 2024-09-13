package com.graymatter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String email;
	private Role role;
}
