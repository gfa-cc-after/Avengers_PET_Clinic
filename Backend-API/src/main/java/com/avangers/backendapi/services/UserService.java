package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;

public interface UserService {
    UserRegistrationResponse addUser(RegisterUserDTO registerUserDTO);
}
