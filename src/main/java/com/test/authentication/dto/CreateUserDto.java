package com.test.authentication.dto;

public class CreateUserDto {

	private String username;

	private String password;

	private UserDetailsDto userDetailsDto;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetailsDto getUserDetailsDto() {
		return userDetailsDto;
	}

	public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
		this.userDetailsDto = userDetailsDto;
	}

	@Override
	public String toString() {
		return "CreateUserDto [username=" + username + ", password=" + password + ", userDetailsDto=" + userDetailsDto
				+ "]";
	}

}
