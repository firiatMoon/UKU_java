package uku.java.JavaBackend.MVC.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uku.java.JavaBackend.MVC.models.Pet;
import uku.java.JavaBackend.MVC.models.User;
import uku.java.JavaBackend.MVC.services.PetService;
import uku.java.JavaBackend.MVC.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldSuccessCreatePet() throws Exception {
        User user = new User(
                1L,
                "Peter",
                "peter@mail.ru",
                23,
                null
        );

        user = userService.createUser(user);
        Assertions.assertNotNull(user.getId());

        Pet pet = new Pet(
                null,
                "name-pet",
                1L
        );

        String petJson = mapper.writeValueAsString(pet);

        String createdPetJson = mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Pet createdPet = mapper.readValue(createdPetJson, Pet.class);

        Assertions.assertNotNull(createdPet.getId());
        Assertions.assertEquals(pet.getName(), createdPet.getName());

    }

    @Test
    void shouldNotCreatePetWhenRequestNotValid() throws Exception {
        Pet pet = new Pet(
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
        User user = new User(
                1L,
                "Peter",
                "peter@mail.ru",
                23,
                null
        );

        user = userService.createUser(user);
        Assertions.assertNotNull(user.getId());

        Pet pet = new Pet(
                null,
                "name-pet",
                1L
        );

        pet = petService.createPet(pet);
        Assertions.assertNotNull(pet.getId());

        mockMvc.perform(delete("/pets/{id}", pet.getId()))
                .andExpect(status().isNoContent());
    }
}