package com.techcareer.userservice.controller;

import java.util.List;

import com.techcareer.userservice.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techcareer.userservice.entity.User;
import com.techcareer.userservice.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "User")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public DataResult<List<User>> getAll(){
		return userService.tumKullanicilar();
	}
	
	
	@PostMapping("/register")
	public DataResult<User> save(@RequestBody User user){
		return userService.register(user);
	}
	
	@GetMapping("{id}")
	public DataResult<User> queryById(@PathVariable("id") Long id){
		return userService.queryById(id);
	}
	
	@GetMapping("/login/{username}/{password}")
	public DataResult<User> login(@PathVariable("username") String username, @PathVariable("password") String password){
		return userService.login(username,password);
	}
	
}
