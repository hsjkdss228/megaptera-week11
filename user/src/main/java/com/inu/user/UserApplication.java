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
// 5. Secret 관리
//    : 문자열, 따라서 코드에 넣지 않고 별도로 관리 >> 설정 파일!
//    @Value 어노테이션을 사용!
//
// 6. 사용자 정보 관리
//    : 사용자를 관리하는 도메인 모델을 마련 >> User, UserRepository
//      Password를 안전하게 보관
//      (평문으로 저장하지 않고 원래 값을 알 수 없는 해시된/암호화된 데이터로 저장)
//      >> 이전에는 BCrypt를 많이 사용
//      >> 최근에는 Argon2라는 비교적 최신인 기법을 사용
//      >> 암호화된 Password를 어떻게 검사할 것인가?
//        1) 입력한 암호를 해시로 만들어서 저장한 해시와 비교
//          (해시를 만들 때마다 같은 값이 나오지 않을 수 있기 때문에 X)
//        2) 뭔가 알고리즘에 맞는 확인 방법이 있으면 좋을 것임, Argon2에는 그 방법이 존재
//
// 이후 과정
// 7. 회원 가입
//    : 로그인이 가능한 User를 마련
// 8. 인가(Authorization)
//    : 이미 만들어진 Access Token으로 실제 서비스에 대한 접근 권한 제어

package com.inu.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
