package com.test.authentication.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.authentication.dto.CreateUserDto;
import com.test.authentication.dto.LoginDto;
import com.test.authentication.dto.UserDetailsDto;
import com.test.authentication.exception.NotLoggedInException;
import com.test.authentication.exception.UserAlreadyExistingException;
import com.test.authentication.exception.UserNotFoundException;
import com.test.authentication.model.User;
import com.test.authentication.model.UserDetails;
import com.test.authentication.repository.UserRepository;
import com.test.authentication.service.UserService;
import com.test.authentication.utils.EncoderUtils;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void loginUser(LoginDto loginDto) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findByUsernameAndPassword(loginDto.getUsername(),
				EncoderUtils.encode(loginDto.getPassword()));
		userOptional.orElseThrow(() -> new UserNotFoundException());

		// Set user as authenticated
		User user = userOptional.get();
		user.setAuthenticated(true);
		userRepository.save(user);
	}

	@Override
	public void logoutUser(String username) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findByUsername(username);
		userOptional.orElseThrow(() -> new UserNotFoundException());

		// Set user as not authenticated
		User user = userOptional.get();
		user.setAuthenticated(false);
		userRepository.save(user);
	}

	@Override
	public UserDetailsDto getUserDetails(String username) throws UserNotFoundException, NotLoggedInException {
		Optional<User> userOptional = userRepository.findByUsername(username);
		userOptional.orElseThrow(() -> new UserNotFoundException());

		// A user must first login into its account to see its user details
		if (!userOptional.get().isAuthenticated())
			throw new NotLoggedInException();

		UserDetails userDetails = userOptional.get().getUserDetails();
		return new UserDetailsDto(userDetails.getUsername(), userDetails.getName(), userDetails.getSurename(),
				userDetails.getAge());
	}

	@Override
	public void createUser(CreateUserDto createUserDto) throws UserAlreadyExistingException {
		Optional<User> userOptional = userRepository.findByUsernameAndPassword(createUserDto.getUsername(),
				EncoderUtils.encode(createUserDto.getPassword()));

		// There could be only one user with a username
		userOptional.ifPresent(user -> {
			throw new UserAlreadyExistingException();
		});

		// Save the new user
		UserDetails userDetails = new UserDetails();
		userDetails.setUsername(createUserDto.getUsername());
		UserDetailsDto userDetailsDto = createUserDto.getUserDetailsDto();
		userDetails.setName(userDetailsDto.getName());
		userDetails.setSurename(userDetailsDto.getSurename());
		userDetails.setAge(userDetailsDto.getAge());

		User user = new User();
		user.setUsername(createUserDto.getUsername());
		user.setPassword(EncoderUtils.encode(createUserDto.getPassword()));
		user.setUserDetails(userDetails);

		userRepository.save(user);
	}
}
