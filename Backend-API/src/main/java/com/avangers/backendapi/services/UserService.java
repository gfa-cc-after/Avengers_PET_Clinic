package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> addUser(UserDTO userDTO);
}
