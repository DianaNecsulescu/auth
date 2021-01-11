package com.test.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.authentication.dto.CreateUserDto;
import com.test.authentication.dto.LoginDto;
import com.test.authentication.dto.UserDetailsDto;
import com.test.authentication.exception.NotLoggedInException;
import com.test.authentication.exception.UserAlreadyExistingException;
import com.test.authentication.exception.UserNotFoundException;
import com.test.authentication.service.UserService;
import com.test.authentication.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsersController {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(UsersController.class);

	private final UserService userService;

	@Autowired
	public UsersController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody CreateUserDto createUserDto) {
		LOGGER.info("Creating new user: " + createUserDto.toString());
		try {
			userService.createUser(createUserDto);
		} catch (UserAlreadyExistingException e) {
			LOGGER.error("Error creating new user: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		LOGGER.info("Login user : " + loginDto.toString());
		try {
			userService.loginUser(loginDto);
		} catch (UserNotFoundException e) {
			LOGGER.error("Error at login user: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody String username) {
		LOGGER.info("Logout user with username: " + username);
		try {
			userService.logoutUser(username);
		} catch (UserNotFoundException e) {
			LOGGER.error("Error at logout user: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/details")
	public ResponseEntity<UserDetailsDto> getUserDetails(
			@RequestParam(value = "username", required = true) String username) {
		LOGGER.info("Get user details for username: " + username);
		UserDetailsDto userDetailsDto = null;
		try {
			userDetailsDto = userService.getUserDetails(username);
		} catch (UserNotFoundException e) {
			LOGGER.error("Error at getting user details: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (NotLoggedInException e) {
			LOGGER.error("Error at getting user details: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userDetailsDto, HttpStatus.OK);
	}

}
