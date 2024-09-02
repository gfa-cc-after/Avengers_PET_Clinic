package com.avangers.backendapi.services;

import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.PetRepository;
import com.avangers.backendapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PetServiceImplTest {
    @Mock
    private PetRepository petRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void addPet() {
        List<Pet> pets = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmail("test@test.com,");
        user.setPassword("test123");
        user.setPets(pets);

        when(userRepository.findByEmail(any())).thenReturn(user)
    }
}