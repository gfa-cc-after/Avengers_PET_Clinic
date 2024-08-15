package com.avangers.backendapi.config;

import com.avangers.backendapi.models.User;
import com.avangers.backendapi.services.JwtTokenServiceImpl;
import com.avangers.backendapi.services.UserService;
import com.avangers.backendapi.services.UserServiceImpl;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import java.util.Base64;
import java.util.Properties;

@SpringBootTest
@ImportAutoConfiguration
@ComponentScan("com.avangers.backendapi")
@ContextConfiguration(classes = {ApplicationConfig.class, UserService.class, UserServiceImpl.class})
class JwtTokenServiceImplTest {


  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void getEmailFromToken() {


  }

  @Test
  void getClaimFromToken() {
  }

  @Test
  void validateToken() {

  }

  @Test
  @DisplayName("Should extract payload from the token ")
  @WithMockUser
  void shouldReturnPayloadFromToken() {


    JwtConfiguration jwtConfiguration = new JwtConfiguration();
    jwtConfiguration.setTokenValidity(400000);
    jwtConfiguration.setSecret("asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdsadasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdsadasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdsadasdasdasdasdasdasd");
    JwtTokenServiceImpl jwtTokenServiceImpl = new JwtTokenServiceImpl(jwtConfiguration);

    User user = User.builder()
            .id(1)
            .email("john.doe@gmail.com")
            .password(passwordEncoder.encode("password"))
            .build();
    String accessToken = jwtTokenServiceImpl.generateToken(user);

    Assertions.assertNotNull(accessToken);
    String[] parts = accessToken.split("\\.");
    Assertions.assertEquals(3, parts.length);
    String subject = jwtTokenServiceImpl.getEmailFromToken(accessToken);
    Assertions.assertTrue(jwtTokenServiceImpl.validateToken(accessToken, user));
    Assertions.assertEquals("john.doe@gmail.com", subject);
  }
}