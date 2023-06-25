package com.mybootapp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.exception.ResourceNotFoundException;
import com.mybootapp.main.model.User;
import com.mybootapp.main.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/add")
	public User add(@RequestBody User user) {
		user.setRole("ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.insert(user);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody User newUser) {
		try {
			userService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
		newUser.setId(id);
		newUser.setRole("ADMIN");
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		return ResponseEntity.status(HttpStatus.OK).body(userService.insert(newUser));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			userService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}