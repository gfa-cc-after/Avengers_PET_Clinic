package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    String addUser(RegisterUserDTO registerUserDTO);

    String updateUser(Long userId, RegisterUserDTO registerUserDTO);
}
