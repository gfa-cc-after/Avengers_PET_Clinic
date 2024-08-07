package com.avangers.backendapi.DTOs;

import lombok.Getter;

@Getter
public record RegisterUserResponseDTO(String email, String message) {

}
