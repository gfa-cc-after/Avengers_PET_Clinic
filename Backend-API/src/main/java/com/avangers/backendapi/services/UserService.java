package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.LoginRequestDTO;
import com.avangers.backendapi.DTOs.LoginResponseDTO;
import com.avangers.backendapi.DTOs.RegisterUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> addUser(RegisterUserDTO registerUserDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
