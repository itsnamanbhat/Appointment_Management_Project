package com.graymatter.dto;


import com.graymatter.entities.DiagnosticCenter;
import com.graymatter.entities.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class PendingDiagnosticCenterRequestDto {
	    private int id;
	    private boolean approved;
	    private String username;
	    private String email;
	    private String password;
	}
