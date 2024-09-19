package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface VetService extends UserDetailsService {
    RegisterUserResponseDTO addVet(RegisterUserRequestDTO registerUserRequestDTO);

    DeleteUserResponseDTO deleteVet(String email);

    UpdateUserResponseDTO updateVet(String email, UpdateUserRequestDTO updateUserRequestDTO);

    LoginUserResponseDTO loginVet(LoginUserRequestDTO loginUserRequestDTO);

    FindUserResponseDTO findVetByEmail(String email);
}
