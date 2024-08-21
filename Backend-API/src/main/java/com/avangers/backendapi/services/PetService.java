package com.avangers.backendapi.services;

import com.avangers.backendapi.models.Pet;

import java.util.List;

public interface PetService {
    List<Pet> getPetsByOwnerId(Long ownerId);
}
