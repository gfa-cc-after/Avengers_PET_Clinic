package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;

public interface UserService {
    UserServiceResponse addUser(RegisterUserDTO registerUserDTO);
}
