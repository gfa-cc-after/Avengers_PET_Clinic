package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import com.avangers.backendapi.DTOs.LoginUserRequestDTO;
import com.avangers.backendapi.DTOs.LoginUserResponseDTO;
import com.avangers.backendapi.DTOs.DeleteUserResponseDTO;
import com.avangers.backendapi.DTOs.UpdateUserRequestDTO;
import com.avangers.backendapi.DTOs.UpdateUserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  RegisterUserResponseDTO addUser(RegisterUserRequestDTO registerUserRequestDTO);
  DeleteUserResponseDTO deleteUser(String email);
  UpdateUserResponseDTO updateUser(String email, UpdateUserRequestDTO updateUserRequestDTO);
  LoginUserResponseDTO loginUser(LoginUserRequestDTO loginUserRequestDTO);
}
