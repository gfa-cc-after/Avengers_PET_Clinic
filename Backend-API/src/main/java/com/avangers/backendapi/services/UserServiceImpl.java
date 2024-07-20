package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserServiceResponse addUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.email())) {
            return new UserServiceResponse("The email already exists");
        }

        User newUser = new User();
        newUser.setEmail(registerUserDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.password()));
        userRepository.save(newUser);

        return new UserServiceResponse("Registration was successful");
    }
}
