package com.sehal.model;

import java.util.ArrayList;
import java.util.List;

//import jakarta.ejb.Stateless;

//@Stateless
public class User {
	private String username;
	private String email;
	private String password;
	public List<User> users = new ArrayList<>();

	public boolean isUser() {
		boolean answer = false;
		return answer;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
