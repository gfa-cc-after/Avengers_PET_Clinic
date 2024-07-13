package com.avangers.backendapi.DTOs;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserDTO(
        @NotBlank(message = "e-mail is required")
        String email,
        @NotBlank(message = "password is required")
        String password){}

