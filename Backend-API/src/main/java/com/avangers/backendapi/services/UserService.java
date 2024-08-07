package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO);
}
