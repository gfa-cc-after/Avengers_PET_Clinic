package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.FindUserResponseDTO;
import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.services.CustomerService;
import com.avangers.backendapi.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final CustomerService customerService;

    @GetMapping("/my-pets")
    public List<Pet> getMyPets(@AuthenticationPrincipal UserDetails userDetails) {
        // gets UserDTO using the username (email)
        FindUserResponseDTO user = customerService.findCustomerByEmail(userDetails.getUsername());
        // Now use the users ID to get the pets
        return petService.getPetsByOwnerId(Long.valueOf(user.getId()));
    }
}
