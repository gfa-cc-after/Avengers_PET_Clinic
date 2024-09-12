package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.*;
import com.avangers.backendapi.event.UserRegistrationEvent;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            // Register the new user using the service
            RegisterUserResponseDTO response = userService.addUser(registerUserRequestDTO);
            // Fetch the newly created user using a method that directly returns a User or User DTO
            FindUserResponseDTO newUserDto = userService.findUserByEmail(registerUserRequestDTO.email());
            // Default verification status to false for new users
            boolean isVerified = false;
            // Publish the user registration event
            User newUser = new User();
            newUser.setId(newUserDto.getId());
            newUser.setEmail(newUserDto.getEmail());
            newUser.setVerified(isVerified);
            eventPublisher.publishEvent(new UserRegistrationEvent(newUser));

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle unexpected errors
            return new ResponseEntity<>("An unexpected error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/users")
    public ResponseEntity<?> updateUser(Principal principal, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        try {
            UpdateUserResponseDTO response = userService.updateUser(principal.getName(), updateUserRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequestDTO loginUserRequestDTO) {
        try {
            return ResponseEntity.ok(userService.loginUser(loginUserRequestDTO));
        } catch (IllegalArgumentException e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteUserResponseDTO> deleteUser(Principal principal) {
        return new ResponseEntity<>(userService.deleteUser(principal.getName()), HttpStatus.OK);
    }
}

