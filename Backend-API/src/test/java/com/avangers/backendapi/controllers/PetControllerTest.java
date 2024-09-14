package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.FindUserResponseDTO;
import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.services.CustomerService;
import com.avangers.backendapi.services.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user@example.com")  // Adds a mock user automatically
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetMyPets() throws Exception {
        // Mocking data
        FindUserResponseDTO userDTO = new FindUserResponseDTO(1, "user@example.com");
        when(customerService.findCustomerByEmail(anyString())).thenReturn(userDTO);

        Pet mockPet = new Pet();
        mockPet.setName("hauko");
        mockPet.setType("hauky");
        List<Pet> pets = Collections.singletonList(mockPet);
        when(petService.getPetsByOwnerId(1L)).thenReturn(pets);

        // Performing the test
        mockMvc.perform(get("/api/pets/my-pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("hauko"))
                .andExpect(jsonPath("$[0].type").value("hauky"));
    }
}
