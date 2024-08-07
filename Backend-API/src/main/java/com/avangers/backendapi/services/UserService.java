package com.avangers.backendapi.services;


import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import com.avangers.backendapi.DTOs.UpdateUserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    RegisterUserResponseDTO addUser(RegisterUserRequestDTO registerUserRequestDTO);
    UpdateUserResponseDTO updateUser(Long userId, RegisterUserRequestDTO registerUserRequestDTO);
}
