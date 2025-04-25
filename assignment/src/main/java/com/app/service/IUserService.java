package com.app.service;

import com.app.dto.UserDto;
import com.app.entity.User;

public interface IUserService {
	
	User addUser(UserDto user);
	
	String updateUser(long id , UserDto user);
	
	String deleteUser(long id );
	

}
