package com.inu.user.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String email;

  // TODO: 암호화된 Password를 받아야 함

  public User() {

  }

  public User(Long id, String email) {
    this.id = id;
    this.email = email;
  }

  public boolean authenticate(String password) {
    return Objects.equals(password, "test");
  }
}
