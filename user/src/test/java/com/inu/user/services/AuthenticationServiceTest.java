package com.inu.user.services;

import com.inu.user.exceptions.LoginFailed;
import com.inu.user.models.User;
import com.inu.user.repositories.UserRepository;
import com.inu.user.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AuthenticationServiceTest {
  @Test
  void authenticate() {
    PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    User user = new User();
    user.changePassword(passwordEncoder, "test");

    UserRepository userRepository = mock(UserRepository.class);

    given(userRepository.getByEmail("tester@example.com"))
        .willReturn(user);
    given(userRepository.getByEmail(null))
        .willThrow(new EntityNotFoundException());
    given(userRepository.getByEmail(""))
        .willThrow(new EntityNotFoundException());
    given(userRepository.getByEmail("asdfasdasd@adsfhad.abert"))
        .willThrow(new EntityNotFoundException());

    JwtUtil jwtUtil = new JwtUtil("MySecret");

    AuthenticationService authenticationService
        = new AuthenticationService(userRepository, passwordEncoder, jwtUtil);

    String token = authenticationService
        .authenticate("tester@example.com", "test");

    assertThat(token).isNotBlank();

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate(null, "test");
    });

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate("", "test");
    });

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate("asdfasdasd@adsfhad.abert", "test");
    });

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate("tester@example.com", "alkhjsvan");
    });
  }
}
