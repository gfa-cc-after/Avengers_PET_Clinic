package com.avangers.backendapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponseDTO {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zipcode;
    private double longitude;
    private double latitude;
    private String description;
}