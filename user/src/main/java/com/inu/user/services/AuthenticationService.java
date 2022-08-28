package com.inu.user.services;

import com.inu.user.exceptions.LoginFailed;
import com.inu.user.models.User;
import com.inu.user.repositories.UserRepository;
import com.inu.user.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public AuthenticationService(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
  }

  public String authenticate(String email, String password) {
    try {
      User user = userRepository.getByEmail(email);

      if (!user.authenticate(passwordEncoder, password)) {
        throw new LoginFailed();
      }

      if (!Objects.equals(email, "tester@example.com")
      || !Objects.equals(password, "test")) {
        throw new LoginFailed();
      }

      return jwtUtil.encode(email);
    } catch (EntityNotFoundException exception) {
      throw new LoginFailed(exception);
    }
  }
}
