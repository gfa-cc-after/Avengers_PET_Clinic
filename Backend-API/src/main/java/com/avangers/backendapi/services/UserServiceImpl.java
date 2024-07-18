package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> addUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.email())) {
            return new ResponseEntity<>("The email is already exists", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(registerUserDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.password()));

        userRepository.save(newUser);

        return new ResponseEntity<>("Registration was successful", HttpStatus.CREATED);
    }

    // Moved registerUser method from UserService <<< THIS
    public void registerUser(String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        // Save the encoded password to the database
    }

    // Moved verifyPassword method from UserService <<< AND THIS THIS
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}