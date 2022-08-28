package com.inu.user.services;

import com.inu.user.exceptions.LoginFailed;
import com.inu.user.utils.JwtUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationServiceTest {
  @Test
  void authenticate() {
    JwtUtil jwtUtil = new JwtUtil();

    AuthenticationService authenticationService
         = new AuthenticationService(jwtUtil);

    String token = authenticationService
        .authenticate("tester@example.com", "test");

    assertThat(token).isNotBlank();

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate(null, "test");
    });

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate("    \t\t  ", "test");
    });

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate("asdfasdasd@adsfhad.abert", "test");
    });

    assertThrows(LoginFailed.class, () -> {
      authenticationService.authenticate("tester@example.com", "alkhjsvan");
    });
  }
}
