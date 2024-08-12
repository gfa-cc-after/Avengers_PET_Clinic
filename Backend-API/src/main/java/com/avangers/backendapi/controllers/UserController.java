package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.DeleteUserResponseDTO;
import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import com.avangers.backendapi.DTOs.LoginRequestDTO;
import com.avangers.backendapi.DTOs.LoginResponseDTO;
import lombok.Value;
import com.avangers.backendapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import java.util.HashMap;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequiredArgsConstructor
public class UserController {


  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
    try {
      RegisterUserResponseDTO response = userService.addUser(registerUserRequestDTO);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      HashMap<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteUserResponseDTO> deleteUser(Principal principal) {
       return new ResponseEntity<>(userService.deleteUser(principal.getName()),HttpStatus.OK);
    }
}
