package com.inu.user.application;

import com.inu.user.dtos.UserRegistrationDto;
import com.inu.user.models.User;
import com.inu.user.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User create(UserRegistrationDto userRegistrationDto) {
    User user = new User(null,
        userRegistrationDto.getName(),
        userRegistrationDto.getEmail());

    user.changePassword(passwordEncoder, userRegistrationDto.getPassword());

    userRepository.save(user);

    return user;
  }
}
