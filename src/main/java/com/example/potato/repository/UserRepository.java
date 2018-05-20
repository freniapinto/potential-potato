package com.example.potato.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.potato.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
