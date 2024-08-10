package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.LoginRequestDTO;
import com.avangers.backendapi.DTOs.LoginResponseDTO;
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
    public ResponseEntity<String> addUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.email())) { // Correct accessor
            return new ResponseEntity<>("The email already exists", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(registerUserDTO.email()); // Correct accessor
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.password())); // Correct accessor
        userRepository.save(newUser);

        return new ResponseEntity<>("Registration was successful", HttpStatus.CREATED);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User userByEmail = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (userByEmail == null) {
            throw new RuntimeException("User email is not valid");
        }
        if (passwordEncoder.matches(loginRequestDTO.getPassword(), userByEmail.getPassword())) {
            // call the token generator inside LoginResponseDTO()
            return new LoginResponseDTO();
        }
        throw new RuntimeException("Password is not valid");
    }
}
