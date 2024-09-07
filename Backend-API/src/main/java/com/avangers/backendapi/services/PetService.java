package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.PetDTO;

import java.util.List;

public interface PetService {
    List<PetDTO> getPetsByOwnerId(Long ownerId);
}
