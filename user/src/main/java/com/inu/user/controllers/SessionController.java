package com.inu.user.controllers;

import com.inu.user.dtos.LoginDto;
import com.inu.user.exceptions.LoginFailed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SessionController {
  @PostMapping("/session")
  @ResponseStatus(HttpStatus.CREATED)
  public String login(
      @RequestBody LoginDto loginDto
  ) {
    // TODO: Service(Application Layer)로 분리되어야 함
    if (!Objects.equals(loginDto.getEmail(), "tester@example.com")
    || !Objects.equals(loginDto.getPassword(), "test")) {
      throw new LoginFailed();
    }

    return "Token";
  }

  @ExceptionHandler(LoginFailed.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String loginFailed() {
    return "Login failed...";
  }
}
