package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody UserDto user) {
		
		System.out.println("abcd");
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUser( Long id, @RequestBody UserDto entity) {
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, entity));
	}
	
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
	}
	
	
	

}
