package com.test.authentication;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.test.authentication.dto.LoginDto;
import com.test.authentication.exception.UserNotFoundException;
import com.test.authentication.model.User;
import com.test.authentication.repository.UserRepository;
import com.test.authentication.service.impl.UserServiceImpl;
import com.test.authentication.utils.EncoderUtils;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;

	UserServiceImpl userService;

	@BeforeEach
	public void setUp() {
		User user = new User();
		Mockito.when(userRepository.findByUsernameAndPassword("admin", EncoderUtils.encode("admin")))
				.thenReturn(Optional.of(user));

		Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

		userService = new UserServiceImpl(userRepository);
	}

	@Test
	public void whenCorrectUserLogin_thenUserShouldBeFound() throws UserNotFoundException {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("admin");
		loginDto.setPassword("admin");

		userService.loginUser(loginDto);
		assertThatNoException();
	}

	@Test
	public void whenIncorrectUserLogin_thenThrowException() throws UserNotFoundException {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("incorrect");
		loginDto.setPassword("admin");

		Mockito.when(userRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.ofNullable(null));

		assertThrows(UserNotFoundException.class, () -> {
			userService.loginUser(loginDto);
		});
	}

	@Test
	public void whenIncorrectUserLogout_thenThrowException() throws UserNotFoundException {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("incorrect");
		loginDto.setPassword("admin");

		Mockito.when(userRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.ofNullable(null));

		assertThrows(UserNotFoundException.class, () -> {
			userService.logoutUser("incorrect");
		});
	}

	@Test
	public void whenCorrectUserLogout_thenUserShouldBeFound() throws UserNotFoundException {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("incorrect");
		loginDto.setPassword("admin");

		userService.logoutUser("admin");
		assertThatNoException();
	}
}
