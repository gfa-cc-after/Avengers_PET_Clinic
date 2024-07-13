package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> addUser(RegisterUserDTO registerUserDTO) {
        // check if someone else is using the email
        if(userRepository.existsByEmail(registerUserDTO.email())){
            return new ResponseEntity<>("The email is already exists", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(registerUserDTO.email());
        newUser.setPassword(registerUserDTO.password());

        userRepository.save(newUser);

        return new ResponseEntity<>("Registration was successful", HttpStatus.CREATED);
    }
}
