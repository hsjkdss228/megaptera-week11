package com.inu.user.controllers;

import com.inu.user.application.UserService;
import com.inu.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @Test
  void register() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"Tester\"," +
                "\"email\":\"tester@example.com\"," +

                "\"password\":\"test\"" +
                "}"))
        .andExpect(status().isCreated())
        .andExpect(content().string(
            containsString("Tester")
        ));
  }
}
