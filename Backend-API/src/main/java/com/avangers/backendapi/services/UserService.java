package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.*;
import com.avangers.backendapi.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    RegisterUserResponseDTO addUser(RegisterUserRequestDTO registerUserRequestDTO);

    DeleteUserResponseDTO deleteUser(String email);

    UpdateUserResponseDTO updateUser(String email, UpdateUserRequestDTO updateUserRequestDTO);

    LoginUserResponseDTO loginUser(LoginUserRequestDTO loginUserRequestDTO);

    FindUserResponseDTO findUserByEmail(String email);

    User findById(Long id); // To find a full User entity by its ID
}
