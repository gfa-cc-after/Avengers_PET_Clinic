package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.repositories.UserRepository;
import com.avangers.backendapi.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private ObjectMapper objectMapper;
//      setUp method is used to create the objectMapper before each test
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

   @Test
   void shouldRregisterUserWithCorrectNameAndPassword() throws Exception {
      RegisterUserDTO validUser = new RegisterUserDTO("user@example.com", "Abc123456");
      given(userServiceImpl.addUser(validUser)).willReturn(ResponseEntity.ok("Registration was successful"));

      // Perform the request and expect 200 OK
      mockMvc.perform(post("/register")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(validUser)))
              .andExpect(status().isOk());
   }
}

