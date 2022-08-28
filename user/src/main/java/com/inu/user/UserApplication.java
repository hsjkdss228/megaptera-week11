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
// 3. AuthenticationService (Application Layer)
//    : authenticate >> E-mail과 Password를 받아 잘 되면 토큰 발행,
//                     잘못되면 예외 발생 (LoginFailed)
//
// 4. JWT (JSON Web Tokens)
//    - 정보를 담고 있는 토큰 (claims)
//    - 내가 발급한 게 맞는지 확인이 필요함 >> 암호학의 서명 기법 사용 (verify)
//    - 정보를 encode >> Token 생성됨 / Token을 decode >> 객체 생성됨
//
// 5. 사용자 정보 관리

package com.inu.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
