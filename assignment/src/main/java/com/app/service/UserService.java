package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.exception.EntityNotFound;
import com.app.repository.IUserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public User addUser(UserDto u) {
		User user = new User();
		
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setTimezone(u.getTimezone());
		user.setActive(true);
		return userRepository.save(user);
	}

	@Override
	public String updateUser(long id, UserDto u) {
		Optional<User> opt = userRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new EntityNotFound("user not found");
		}
		
		User user = new User();
		
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setId(u.getId());
		user.setTimezone(u.getTimezone());
		user.setActive(true);
		
		userRepository.save(user); 
		
		return "user updated successfully";
		
	}

	@Override
	public String deleteUser(long id) {
	
		User user = userRepository.findById(id).orElseThrow(()->new EntityNotFound("this user not exist"));
		user.setActive(false);
		
		userRepository.save(user);
		return "updated successfully";
	}

}
