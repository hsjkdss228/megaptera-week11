package com.inu.user.services;

import com.inu.user.exceptions.LoginFailed;
import com.inu.user.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
  private final JwtUtil jwtUtil;

  public AuthenticationService(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  public String authenticate(String email, String password) {
    if (!Objects.equals(email, "tester@example.com")
        || !Objects.equals(password, "test")) {
      throw new LoginFailed();
    }

    return jwtUtil.encode(email);
  }
}
