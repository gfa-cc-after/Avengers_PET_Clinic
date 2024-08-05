package com.avangers.backendapi.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String email;
    private String message;

    public UserResponseDTO(String email, String message) {
        this.email = email;
        this.message = message;
    }
}
