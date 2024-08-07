package com.avangers.backendapi.controllers;


import com.avangers.backendapi.DTOs.DeleteUserResponseDTO;
import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.services.UserService;
import com.avangers.backendapi.services.UserRegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        UserRegistrationResponse response = userService.addUser(registerUserDTO);

        HttpStatus status = "The email already exists".equals(response.getMessage()) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(response.getMessage(), status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteUserResponseDTO> deleteUser(Principal principal) {
        return new ResponseEntity<>(userService.deleteUser(principal.getName()),HttpStatus.OK);
    }
}