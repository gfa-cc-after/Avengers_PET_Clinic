package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {
    RegisterUserResponseDTO addCustomer(RegisterUserRequestDTO registerUserRequestDTO);

    DeleteUserResponseDTO deleteCustomer(String email);

    UpdateUserResponseDTO updateCustomer(String email, UpdateUserRequestDTO updateUserRequestDTO);

    LoginUserResponseDTO loginCustomer(LoginUserRequestDTO loginUserRequestDTO);

    FindUserResponseDTO findCustomerByEmail(String email);
}
