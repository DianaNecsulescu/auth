package com.test.authentication.service;

import com.test.authentication.dto.CreateUserDto;
import com.test.authentication.dto.LoginDto;
import com.test.authentication.dto.UserDetailsDto;
import com.test.authentication.exception.NotLoggedInException;
import com.test.authentication.exception.UserAlreadyExistingException;
import com.test.authentication.exception.UserNotFoundException;

public interface UserService {

	public void createUser(CreateUserDto createUserDto)
			throws UserAlreadyExistingException;

	public void loginUser(LoginDto loginDto) throws UserNotFoundException;

	public void logoutUser(String username) throws UserNotFoundException;

	public UserDetailsDto getUserDetails(String username) throws UserNotFoundException, NotLoggedInException;
}
