package com.inu.user.controllers;

import com.inu.user.dtos.LoginDto;
import com.inu.user.exceptions.LoginFailed;
import com.inu.user.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
  private final AuthenticationService authenticationService;

  public SessionController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/session")
  @ResponseStatus(HttpStatus.CREATED)
  public String login(
      @RequestBody LoginDto loginDto
  ) {
    return authenticationService.authenticate(
        loginDto.getEmail(), loginDto.getPassword());
  }

  @ExceptionHandler(LoginFailed.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String loginFailed() {
    return "Login failed...";
  }
}
