package com.graymatter.dto;


import com.graymatter.entities.Appointment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResultDto {
	private int id;
	@NotNull(message = "Test Name cannot be null")
    @Size(min = 1, max = 100, message = "Test Name must be between 1 and 100 characters")
	private String testName;
	
    
    @NotNull(message = "Appointment cannot be null")
	private Appointment appointment;

}
