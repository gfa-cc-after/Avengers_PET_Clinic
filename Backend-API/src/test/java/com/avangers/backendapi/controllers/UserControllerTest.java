package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.repositories.UserRepository;
import com.avangers.backendapi.services.UserRegistrationResponse;
import com.avangers.backendapi.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @MockBean
  private UserServiceImpl userServiceImpl;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PasswordEncoder passwordEncoder;

  //      objectMapper is used to convert objects to JSON
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @DisplayName("Should return 201 ok if request is valid")
  @Test
  void shouldRegisterUserWithCorrectNameAndPassword() throws Exception {
    RegisterUserDTO validUser = new RegisterUserDTO("user@example.com", "Abc123456");
    UserRegistrationResponse mockReturn = new UserRegistrationResponse();
    mockReturn.setMessage("Registration was successful");
    given(userServiceImpl.addUser(validUser)).willReturn(mockReturn);

    mockMvc.perform(post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validUser)))
            .andExpect(status().isCreated());
  }

  @DisplayName("Should return 400 bad request if password is not valid")
  @Test
  void shouldNotRegisterWithBadPassword() throws Exception {
    RegisterUserDTO userWithBadPassword = new RegisterUserDTO("user@example.com", "badpassword");
    UserRegistrationResponse response = new UserRegistrationResponse();
    response.setMessage("Password should contain at least one uppercase and one lowercase letter");
    given(userServiceImpl.addUser(userWithBadPassword)).willReturn(response);

    mockMvc.perform(post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userWithBadPassword)))
            .andExpect(status().isBadRequest());
  }
}

