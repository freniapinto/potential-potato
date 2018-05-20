package com.example.potato.service;

import com.example.potato.model.User;

public interface UserService {
	
	void save(User user);
	
	User findByUsername(String name);

}
