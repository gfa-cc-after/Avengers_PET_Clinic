package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.FindUserResponseDTO;
import com.avangers.backendapi.DTOs.PetDTO;
import com.avangers.backendapi.models.Pet;
import com.avangers.backendapi.services.PetService;
import com.avangers.backendapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
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
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Should return list of pets owned by the user")
    @Test
    public void testGetMyPets() throws Exception {
        // Mocking data using DTO
        FindUserResponseDTO userDTO = new FindUserResponseDTO(1, "user@example.com");
        when(userService.findUserByEmail(anyString())).thenReturn(userDTO);

        // Using PetDTO for mock return value
        PetDTO mockPetDTO = new PetDTO();
        mockPetDTO.setName("hauko");
        mockPetDTO.setType("hauky");

        List<PetDTO> petsDTOs = Collections.singletonList(mockPetDTO);
        when(petService.getPetsByOwnerId(1L)).thenReturn(petsDTOs);

        // Performing the test
        mockMvc.perform(get("/api/pets/my-pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("hauko"))
                .andExpect(jsonPath("$[0].type").value("hauky"));
    }
}
