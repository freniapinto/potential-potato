package com.example.potato.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	private Long id;
	
	@Column(name ="username")
	private String username;
	
	@Column(name ="password")
	private String password;
	
	public User() {
		
	}
	
	public User(String name, String pass) {
		username = name;
		password = pass;
	}
	
	/**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    
    /**
     * The function sets id associated with the User object
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * The function sets the username associated with User object
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * The function sets the password associated with User object
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
