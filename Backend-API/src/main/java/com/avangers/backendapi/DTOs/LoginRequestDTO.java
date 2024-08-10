package com.avangers.backendapi.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
@Data
public class LoginRequestDTO {
    @Email
    private String email;
    @NotBlank
    private String password;
}
