package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.DTOs.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponseDTO addUser(RegisterUserDTO registerUserDTO);
    UserResponseDTO updateUser(Long userId, RegisterUserDTO registerUserDTO);

}
