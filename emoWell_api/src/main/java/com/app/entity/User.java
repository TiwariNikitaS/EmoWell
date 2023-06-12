package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Range;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String name;
	@Range(min = 65, max = 100)
	private int age;
	@Email
	private String email;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int u_id, String name, int age, String email) {
		super();
		this.user_id = u_id;
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int u_id) {
		this.user_id = u_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
