package com.avangers.backendapi.controllers;


import com.avangers.backendapi.DTOs.LoginUserRequestDTO;
import com.avangers.backendapi.DTOs.LoginUserResponseDTO;
import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @DisplayName("Should login and get a valid JWT token")
    @Test
    public void shouldLoginAndGetToken() throws Exception {

        // Given
        String email = "john.doe@gmail.com";
        String password = "Abc123456";

        User foundUser = new User();
        foundUser.setEmail(email);
        foundUser.setPassword(passwordEncoder.encode(password));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(foundUser));


        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO();
        loginUserRequestDTO.setEmail(email);
        loginUserRequestDTO.setPassword(password);

        // When

        // @formatter:off
        MvcResult loginResponse =  mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserRequestDTO)))
                .andReturn();

        LoginUserResponseDTO responseDTO = objectMapper.readValue(
                loginResponse.getResponse().getContentAsString(),
                LoginUserResponseDTO.class);
        // @formatter:on


        // Then
        Assertions.assertEquals(200, loginResponse.getResponse().getStatus());
        Assertions.assertEquals(responseDTO.token().split("\\.").length, 3);
    }


    @DisplayName("Should login and get a valid JWT token and use that token to delete self")
    @Test
    public void shouldLoginAndGetTokenAndDeleteAfter() throws Exception {

        // Given
        String email = "john.doe@gmail.com";
        String password = "Abc123456";

        User foundUser = new User();
        foundUser.setEmail(email);
        foundUser.setPassword(passwordEncoder.encode(password));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(foundUser));


        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO();
        loginUserRequestDTO.setEmail(email);
        loginUserRequestDTO.setPassword(password);


        // @formatter:off
        MvcResult loginResponse =  mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserRequestDTO)))
                .andReturn();

        LoginUserResponseDTO responseDTO = objectMapper.readValue(
                loginResponse.getResponse().getContentAsString(),
                LoginUserResponseDTO.class);
        // @formatter:on

        mockMvc.perform(delete("/delete")
                        .header("Authorization", "Bearer " + responseDTO.token())
                )
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    @DisplayName("If user want to register with an empty password it should return with an error about short password")
    public void shouldReturnErrorMessageAndStatusCodeWhenRegisteredWithEmptyPassword() throws Exception {

        RegisterUserRequestDTO registerUserRequestDTO = new RegisterUserRequestDTO("john.doe@gmail.com", "");
        String httpBody = objectMapper.writeValueAsString(registerUserRequestDTO);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBody)
                )
                .andExpectAll(
                        status().is4xxClientError(),
                        jsonPath("$.error", org.hamcrest.Matchers.is("password should contain at least one uppercase and one lowercase letter"))
                );

    }
}
