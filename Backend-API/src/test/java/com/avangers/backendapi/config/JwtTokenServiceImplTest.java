package com.avangers.backendapi.config;

import com.avangers.backendapi.services.JwtTokenServiceImpl;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Base64;

class JwtTokenServiceImplTest {


    @Test
    void getEmailFromToken(){


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

        JwtTokenServiceImpl jwtTokenServiceImpl = new JwtTokenServiceImpl();


        String testingToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.VFb0qJ1LRg_4ujbZoRMXnVkUgiuKq5KxWqNdbKq_G9Vvz-S1zZa9LPxtHWKa64zDl2ofkT8F6jBt_K4riU-fPg";

        JsonObject claims = new JsonObject().getAsJsonObject();
        claims.addProperty("sub","1234567890");
        claims.addProperty("name","John Doe");
        claims.addProperty("admin",true);
        claims.addProperty("iat",1516239022);

        Base64.Decoder decoder = Base64.getUrlDecoder();
//
        String[] separatedClaims = testingToken.split("\\.");
//
        String payload = new String(decoder.decode(separatedClaims[1]));

        Assertions.assertNotNull(payload);
        Assertions.assertEquals(jwtTokenServiceImpl., payload);
    }
}