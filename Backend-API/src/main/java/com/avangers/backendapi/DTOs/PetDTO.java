package com.avangers.backendapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private Long id;
    private String name;
    private String type;
}
