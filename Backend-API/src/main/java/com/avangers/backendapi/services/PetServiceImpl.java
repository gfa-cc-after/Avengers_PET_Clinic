package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.AddPetRequestDTO;
import com.avangers.backendapi.DTOs.AddPetResponseDTO;
import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.PetRepository;
import com.avangers.backendapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Override
    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    @Override
    public AddPetResponseDTO addPet(AddPetRequestDTO addPetRequestDTO, String email) {

        //check if the user exists
        User petOwner = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        if(petOwner.getPets().contains(addPetRequestDTO)) {
            new RuntimeException("Pet with " + addPetRequestDTO.name() + " already exists");
        }

        Pet newPet = new Pet();
        newPet.setName(addPetRequestDTO.name());
        newPet.setType(addPetRequestDTO.type());
        newPet.setOwner(petOwner);

        petRepository.save(newPet);

        return new AddPetResponseDTO();
    }
}
