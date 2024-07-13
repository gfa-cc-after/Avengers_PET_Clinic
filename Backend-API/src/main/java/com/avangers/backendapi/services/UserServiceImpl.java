package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.UserDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> addUser(UserDTO userDTO) {
        // check if someone else is using the email
        if(userRepository.existsByEmail(userDTO.getEmail())){
            return new ResponseEntity<>("The email is already exists", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

        userRepository.save(newUser);

        return new ResponseEntity<>("Registration was successful", HttpStatus.CREATED);
    }
}
