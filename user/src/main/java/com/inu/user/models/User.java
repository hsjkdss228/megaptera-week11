package com.inu.user.models;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String email;

  private String password;

  public User() {

  }

  public User(Long id, String email) {
    this.id = id;
    this.email = email;
  }

  public boolean authenticate(PasswordEncoder passwordEncoder,
                              String password) {
    return passwordEncoder.matches(password, this.password);

//    return Objects.equals(this.password, passwordEncoder.encode(password));
  }

  public void changePassword(PasswordEncoder passwordEncoder,
                             String password) {
    this.password = passwordEncoder.encode(password);
  }
}
