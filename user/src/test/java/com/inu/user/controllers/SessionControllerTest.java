package com.inu.user.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void loginWithRightEmailAndPassword() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"email\":\"tester@example.com\"," +
                "\"password\":\"test\"" +
                "}"))
        .andExpect(status().isCreated());
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
