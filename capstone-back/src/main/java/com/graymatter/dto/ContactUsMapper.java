
package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.ContactUs;


@Component
public class ContactUsMapper {
	public ContactUs mapToContactUs(ContactUsDto cDto) {
		return new ContactUs(cDto.getId(),cDto.getFirstName(),cDto.getLastName(),cDto.getEmail(),cDto.getPhone(),cDto.getMessage());
	}
	
	public ContactUsDto mapToContactUsDto(ContactUs c) {
		return new ContactUsDto(c.getId(),c.getFirstName(),c.getLastName(),c.getEmail(),c.getPhone(),c.getMessage());
	}
}
