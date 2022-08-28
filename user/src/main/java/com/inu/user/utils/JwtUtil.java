package com.inu.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final Algorithm algorithm;

  public JwtUtil(@Value("${jwt.secret}") String secret) {
    algorithm = Algorithm.HMAC256(secret);
  }

  // encode >> Token 생성
  public String encode(String email) {

    String token = JWT.create()
        .withClaim("email", email)
        .sign(algorithm);

    return token;
  }

  // decode >> Token 해석
  public String decode(String token) {
    JWTVerifier verifier = JWT.require(algorithm).build();

    DecodedJWT jwt = verifier.verify(token);

    return jwt.getClaim("email").asString();
  }
}
