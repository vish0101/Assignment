package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private long id;
	
	private String firstName;

	private String lastName;
	
	private String timezone;

	private boolean isActive;
}
