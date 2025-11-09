package uku.java.JavaBackend.MVC.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uku.java.JavaBackend.MVC.models.PetDTO;
import uku.java.JavaBackend.MVC.services.PetService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetService petService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldSuccessCreatePet() throws Exception {
        PetDTO pet = new PetDTO(
                null,
                "name-pet",
                2L
        );

        String petJson = mapper.writeValueAsString(pet);

        String createdPetJson = mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PetDTO createdPet = mapper.readValue(createdPetJson, PetDTO.class);

        Assertions.assertNotNull(createdPet.getId());
        Assertions.assertEquals(pet.getUser(), createdPet.getUser());
    }

    @Test
    void shouldNotCreatePetWhenRequestNotValid() throws Exception {
        PetDTO pet = new PetDTO(
                null,
                "name-pet",
                null
        );

        String userJson = mapper.writeValueAsString(pet);

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDeletePet() throws Exception {
        PetDTO pet = new PetDTO(
                null,
                "name-pet",
                2L
        );

        pet = petService.createPet(pet);

        mockMvc.perform(delete("/pets/{id}", pet.getId()))
                .andExpect(status().isNoContent());
    }
}