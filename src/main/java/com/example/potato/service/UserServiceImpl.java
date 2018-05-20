package com.example.potato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.potato.model.User;
import com.example.potato.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		userRepository.save(user);	
	}

	@Override
	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}

}
