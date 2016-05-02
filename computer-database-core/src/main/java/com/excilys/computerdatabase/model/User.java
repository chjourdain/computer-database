package com.excilys.computerdatabase.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.excilys.computerdatabase.model.roles.UserRole;

@Entity
@Table(name = "users")
public class User {

	private String username;
	private String password;
	private UserRole userRole;

	public User() {
	}

	public User(String username, String password, UserRole role) {
		this.username = username;
		this.password = password;
		this.userRole = role;
	}

	@Id
	@Column(name = "username", unique = true, 
		nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
	    return userRole;
	}

	public void setUserRole(UserRole userRole) {
	    this.userRole = userRole;
	}
}
