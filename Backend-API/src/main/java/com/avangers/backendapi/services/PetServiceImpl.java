package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.PetDTO;
import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public List<PetDTO> getPetsByOwnerId(Long ownerId) {
        List<Pet> pets = petRepository.findByOwnerId(ownerId);
        return pets.stream()
                .map(pet -> new PetDTO(pet.getId(), pet.getName(), pet.getType()))
                .collect(Collectors.toList());
    }
}
