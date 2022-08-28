// 1. Spring Security의 기본 기능 사용 X (REST API이기 때문에 기본 기능 불필요)
//
// 2. 로그인
// >> [E-mail Address]과 [Password] 입력 >> 사용자 인증(authentication) 처리
// >> Access Token 발급
// >> REST API로 어떻게 표현할 것인가?
//    Resource(복수형) + HTTP Method(CRUD 구분)
//    1) Session(단수형) >> Create  ***여기서는 Session 방법을 사용***
//    2) Token(단수형) >> Create
//    3) Auth(단수형) >> Create
//    등등의 방법이 있음
//    - 입력값 처리를 위한 Dto 생성
//
// 3. JWT

package com.inu.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
