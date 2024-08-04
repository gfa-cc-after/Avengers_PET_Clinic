package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ResponseEntity<String> addUser(RegisterUserDTO registerUserDTO);
    String updateUser(Long userId, RegisterUserDTO registerUserDTO);

}
