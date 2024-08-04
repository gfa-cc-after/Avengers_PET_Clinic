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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String addUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.email())) { // Correct accessor
            return "The email already exists";
        }

        User newUser = new User();
        newUser.setEmail(registerUserDTO.email()); // Correct accessor
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.password())); // Correct accessor
        userRepository.save(newUser);

        return "Registration was successful";
    }

    @Override
    public String updateUser(Long userId, RegisterUserDTO registerUserDTO) {
        User existingUser = userRepository.findById(userId).orElse(null);
//        check if User exists
        if (existingUser == null) {
            return "User not found";
        }
//        check if new email is not already used by another user
        if (!existingUser.getEmail().equals(registerUserDTO.email()) && userRepository.existsByEmail(registerUserDTO.email())) {
            return "The email already exist";
        }

        existingUser.setEmail(registerUserDTO.email());
        existingUser.setPassword(passwordEncoder.encode(registerUserDTO.password()));
        userRepository.save(existingUser);

        return "Update was successful";
    }


}
