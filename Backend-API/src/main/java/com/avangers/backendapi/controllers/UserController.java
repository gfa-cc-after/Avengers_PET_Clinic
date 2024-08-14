package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.*;
import com.avangers.backendapi.DTOs.LoginUserResponseDTO;
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
  }
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(@Valid @RequestBody LoginUserRequestDTO loginUserRequestDTO) {
        return ResponseEntity.ok(userService.loginUser(loginUserRequestDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteUserResponseDTO> deleteUser(Principal principal) {
       return new ResponseEntity<>(userService.deleteUser(principal.getName()),HttpStatus.OK);
    }
}
