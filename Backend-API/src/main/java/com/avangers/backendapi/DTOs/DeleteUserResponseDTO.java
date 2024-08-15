package com.avangers.backendapi.DTOs;

import lombok.Builder;

@Builder
public record DeleteUserResponseDTO (
        String response
) {
}
