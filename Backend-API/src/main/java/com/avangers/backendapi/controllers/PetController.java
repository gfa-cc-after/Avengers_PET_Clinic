package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.AddPetRequestDTO;
import com.avangers.backendapi.DTOs.AddPetResponseDTO;
import com.avangers.backendapi.DTOs.FindUserResponseDTO;
import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.services.PetService;
import com.avangers.backendapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final UserService userService;

    @GetMapping("/my-pets")
    public List<Pet> getMyPets(@AuthenticationPrincipal UserDetails userDetails) {
        // gets UserDTO using the username (email)
        FindUserResponseDTO user = userService.findUserByEmail(userDetails.getUsername());
        // Now use the users ID to get the pets
        return petService.getPetsByOwnerId(Long.valueOf(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<AddPetResponseDTO> addPet(@Valid @RequestBody AddPetRequestDTO addPetRequestDTO, Principal principal) {
 return new ResponseEntity<>(petService.addPet(addPetRequestDTO, principal ), HttpStatus.OK);
    }

}
