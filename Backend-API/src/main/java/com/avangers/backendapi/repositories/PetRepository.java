package com.avangers.backendapi.repositories;

import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByOwnerId(Long ownerId);

    Optional<Pet> findByName(String email);
}