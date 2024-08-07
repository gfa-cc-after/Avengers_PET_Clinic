package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.DeleteUserResponseDTO;
import com.avangers.backendapi.DTOs.RegisterUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegistrationResponse addUser(RegisterUserDTO registerUserDTO);
    DeleteUserResponseDTO deleteUser(String email);
}
