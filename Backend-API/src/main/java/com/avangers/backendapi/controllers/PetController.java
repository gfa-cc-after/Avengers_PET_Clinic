package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.FindUserResponseDTO;
import com.avangers.backendapi.DTOs.PetDTO;
import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.services.PetService;
import com.avangers.backendapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final UserService userService;

    @GetMapping("/my-pets")
    public List<PetDTO> getMyPets(Principal principal) {
        // gets UserDTO using the username (email)
        FindUserResponseDTO user = userService.findUserByEmail(principal.getName());
        // Now use the user's ID to get the pets
        return petService.getPetsByOwnerId(Long.valueOf(user.getId()));
    }
}
