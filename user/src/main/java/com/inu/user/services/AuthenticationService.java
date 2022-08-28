package com.inu.user.services;

import com.inu.user.exceptions.LoginFailed;

import java.util.Objects;

public class AuthenticationService {
  public String authenticate(String email, String password) {
    if (!Objects.equals(email, "tester@example.com")
        || !Objects.equals(password, "test")) {
      throw new LoginFailed();
    }

    return "Access Token!";
  }
}
