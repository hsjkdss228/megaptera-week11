package com.inu.user.application;

import com.inu.user.exceptions.LoginFailed;
import com.inu.user.models.User;
import com.inu.user.repositories.UserRepository;
import com.inu.user.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
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

      if (user == null
          || !user.authenticate(passwordEncoder, password)) {
        throw new LoginFailed();
      }

      return jwtUtil.encode(email);
    } catch (EntityNotFoundException exception) {
      throw new LoginFailed(exception);
    }
  }
}
