package com.inu.user.controllers;

import com.inu.user.models.User;
import com.inu.user.repositories.UserRepository;
import com.inu.user.services.AuthenticationService;
import com.inu.user.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private AuthenticationService authenticationService;

  @MockBean
  private UserRepository userRepository;

  @SpyBean
  private PasswordEncoder passwordEncoder;

  @SpyBean
  private JwtUtil jwtUtil;

  @Test
  void loginWithRightEmailAndPassword() throws Exception {
    User user = new User();
    user.changePassword(passwordEncoder, "test");

    given(userRepository.getByEmail("tester@example.com"))
        .willReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"email\":\"tester@example.com\"," +
                "\"password\":\"test\"" +
                "}"))
        .andExpect(status().isCreated())
//        .andExpect(content().string(
//            containsString("너의 Encoded code를 보여줘...!")
//        ))
    ;
  }

  @Test
  void loginWithWrongEmail() throws Exception {
    given(userRepository.getByEmail(any()))
        .willThrow(new EntityNotFoundException());

    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"email\":\"asdhfkjah@adfkdsfla.adsgew\"," +
                "\"password\":\"test\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void loginWithWrongPassword() throws Exception {
    User user = new User();
    user.changePassword(passwordEncoder, "test");

    given(userRepository.getByEmail("tester@example.com"))
        .willReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"email\":\"tester@example.com\"," +
                "\"password\":\"asdfgadsfgj\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }
}
