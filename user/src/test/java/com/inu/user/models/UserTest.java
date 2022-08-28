package com.inu.user.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

  @Test
  void authenticate() {
    User user = new User();

    assertThat(user.authenticate("test")).isTrue();
    assertThat(user.authenticate("asdfgadsjkb")).isFalse();
  }
}
