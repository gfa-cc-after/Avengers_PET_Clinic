package com.avangers.backendapi.controllers;


import com.avangers.backendapi.DTOs.UserDTO;
import com.avangers.backendapi.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO user){
        return userServiceImpl.addUser(user);
    }
}
