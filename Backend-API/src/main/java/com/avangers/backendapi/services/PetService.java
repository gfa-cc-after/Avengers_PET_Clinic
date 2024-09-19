package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.AddPetRequestDTO;
import com.avangers.backendapi.DTOs.AddPetResponseDTO;
import com.avangers.backendapi.DTOs.PetDTO;

import java.util.List;

public interface PetService {

    AddPetResponseDTO addPet(AddPetRequestDTO addPetRequestDTO, String email);

    List<PetDTO> getPetsByOwnerId(Long ownerId);
}
