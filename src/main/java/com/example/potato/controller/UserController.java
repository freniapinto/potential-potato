package com.example.potato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.potato.model.User;
import com.example.potato.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getUser", params= {"username"}, method= RequestMethod.GET)
	public User getUser(@RequestParam("username") String username) {
		return userService.findByUsername(username);
	}
	
	@RequestMapping(value="/postUser", params= {"username","password"}, method= RequestMethod.POST)
	public void postUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		userService.save(new User(username, password));
	}

}
