package com.test.authentication.dto;

public class UserDetailsDto {

	private String username;

	private String name;

	private String surename;

	private Integer age;

	public UserDetailsDto(String username, String name, String surename, Integer age) {
		super();
		this.username = username;
		this.name = name;
		this.surename = surename;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserDetailsDto [username=" + username + ", name=" + name + ", surename=" + surename + ", age=" + age
				+ "]";
	}

}
