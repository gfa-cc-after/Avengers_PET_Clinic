package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.AddPetRequestDTO;
import com.avangers.backendapi.DTOs.AddPetResponseDTO;
import com.avangers.backendapi.models.Pet;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> getPetsByOwnerId(Long ownerId);

    AddPetResponseDTO addPet(AddPetRequestDTO addPetRequestDTO, String email);

}
