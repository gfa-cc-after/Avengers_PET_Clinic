package com.avangers.backendapi.services;

import com.google.gson.JsonObject;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @DisplayName("The email should be correctly extracted from correct token")
    @Test
    void correctlyExtractedEmailFromCorrectToken(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInBhc3N3b3JkIjoicGFzc3dvcmQiLCJpYXQiOjExMTExMTExMTF9.GZ0jNkCaAVA0zSYxt79EGtDrtGSKsR_OrXze7pAUYM8";
        String result = "test@email.com";

        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("sub", "test@email.com");

        assertEquals(jwtService.extractEmail(token), result);
    }
    @DisplayName("Email could not be extracted from wrong token")
    @Test
    void emailShouldNotBeExtractedFromWrongToken(){

        String wrongToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ3cm9uZ2VtYWlsQGVtYWlsLmNvbSIsInBhc3N3b3JkIjoicGFzc3dvcmQiLCJpYXQiOjExMTExMTExMTF9.mUoABPm25kw4-4NEgx62w2SJJCSpYTdbNRDkom1Ewvg";
        String result = "test@email.com";

        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("sub", "test@email.com");

        assertNotEquals(jwtService.extractEmail(wrongToken), result);
    }

}