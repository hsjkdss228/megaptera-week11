package com.inu.user.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {
  @Test
  void encodeAndDecode() {
    final String email = "tester@example.com";

    JwtUtil jwtUtil = new JwtUtil();

    String token = jwtUtil.encode(email);

    System.out.println(token);

    assertThat(token).isNotBlank();
    assertThat(token).contains(".");

    assertThat(jwtUtil.decode(token)).isEqualTo(email);
  }
}
