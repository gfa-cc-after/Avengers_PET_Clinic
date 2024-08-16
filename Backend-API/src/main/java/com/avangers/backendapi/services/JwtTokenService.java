package com.avangers.backendapi.services;

import com.avangers.backendapi.models.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    String generateToken(User user);

}
