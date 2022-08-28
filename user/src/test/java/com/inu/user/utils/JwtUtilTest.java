package com.inu.user.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {
  @Test
  void encodeAndDecode() {
    final String email = "tester@example.com";

    JwtUtil jwtUtil = new JwtUtil("MySecret");

    String token = jwtUtil.encode(email);

    assertThat(token).isNotBlank();
//    assertThat(token).contains("너의 Encoded code를 보여줘...!");

    assertThat(jwtUtil.decode(token)).isEqualTo(email);
  }
}
