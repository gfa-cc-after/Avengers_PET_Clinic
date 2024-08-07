package com.avangers.backendapi.controllers;


import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.services.UserService;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> register(@Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        return new ResponseEntity<>(userService.registerUser(registerUserRequestDTO), HttpStatus.CREATED);
    }
}