package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> addUser(RegisterUserDTO registerUserDTO);
    ResponseEntity<String> updateUser(Long userId, RegisterUserDTO registerUserDTO);
}
