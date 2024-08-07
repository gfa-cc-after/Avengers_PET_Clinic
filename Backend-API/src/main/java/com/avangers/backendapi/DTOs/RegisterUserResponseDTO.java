package com.avangers.backendapi.DTOs;

import lombok.Builder;

@Builder
public record RegisterUserResponseDTO(
        String response
) { }
