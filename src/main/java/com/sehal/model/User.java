package com.sehal.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class User {

	private Long id_user;
	private String username;
	private String email;
	private String password;
	public List<User> users = new ArrayList<>();

	public boolean isUser() {
		boolean answer = false;
		return answer;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
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
