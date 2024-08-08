package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  RegisterUserResponseDTO addUser(RegisterUserRequestDTO registerUserRequestDTO);
  RegisterUserResponseDTO updateUser(Long userId, RegisterUserRequestDTO updateUserRequestDTO);
  // Add more user-related methods here in the future if needed
}
