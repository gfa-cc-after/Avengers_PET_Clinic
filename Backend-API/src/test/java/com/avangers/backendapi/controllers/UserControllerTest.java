package com.avangers.backendapi.controllers;


import com.avangers.backendapi.DTOs.DeleteUserResponseDTO;
import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import com.avangers.backendapi.config.SecurityConfig;
import com.avangers.backendapi.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
class UserControllerTest {

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @DisplayName("Should return 201 OK if request is valid")
    @Test
    void shouldRegisterUserWithCorrectNameAndPassword() throws Exception {
        RegisterUserRequestDTO validUser = new RegisterUserRequestDTO("user@example.com", "Abc123456");

        given(userServiceImpl.addUser(validUser)).willReturn(new RegisterUserResponseDTO());

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUser)))
                .andExpect(status().isCreated());
    }

    @DisplayName("Should return 400 BAD REQUEST if password is not valid")
    @Test
    void shouldNotRegisterWithBadPassword() throws Exception {
        RegisterUserRequestDTO userWithBadPassword = new RegisterUserRequestDTO("user@example.com", "badpassword");
        given(userServiceImpl.addUser(userWithBadPassword)).willThrow(new IllegalArgumentException("Password is not valid"));

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userWithBadPassword)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Should return 200 if user was successfully deleted")
    @Test
    @WithMockUser
    void shouldDeleteUserAndReturn200() throws Exception {

        String username = "test@email.com";

        when(userServiceImpl.deleteUser("test@email.com")).thenReturn(
                new DeleteUserResponseDTO("user@email.com")
        );
        mockMvc.perform(delete("/delete", username))
                .andExpect(status().is(200));
    }
}
