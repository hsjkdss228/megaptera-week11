package com.inu.user.controllers;

import com.inu.user.services.AuthenticationService;
import com.inu.user.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private AuthenticationService authenticationService;

  @SpyBean
  private JwtUtil jwtUtil;

  @Test
  void loginWithRightEmailAndPassword() throws Exception {
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
    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"email\":\"tester@example.com\"," +
                "\"password\":\"asdfgadsfgj\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }
}
