package com.avangers.backendapi.services;

import com.avangers.backendapi.models.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public interface JwtTokenService {
    String getEmailFromToken(String token);
    Date getExpirationDateFromToken(String token);
    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
    String generateToken(User user);
    Boolean validateToken(String token, UserDetails userDetails);


}
