package com.avangers.backendapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FindUserResponseDTO {
    private Long id;
    private String email;
}
