package com.avangers.backendapi.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserRequestDTO {
    @Email
    private String email;
    @NotBlank
    private String password;
}
