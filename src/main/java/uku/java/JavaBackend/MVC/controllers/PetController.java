package uku.java.JavaBackend.MVC.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uku.java.JavaBackend.MVC.dto.PetDTO;
import uku.java.JavaBackend.MVC.models.Pet;
import uku.java.JavaBackend.MVC.services.PetService;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        List<PetDTO> petsDTO = pets.stream().map(petService::convertToPetDTO).toList();

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(petsDTO);
    }

    @PostMapping
    public ResponseEntity<PetDTO> createPet(@RequestBody @Valid Pet pet) {
        Pet createdPet = petService.createPet(pet);
        PetDTO petDTO = petService.convertToPetDTO(createdPet);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable("id") Long id) {
        Pet pet = petService.getPetById(id);
        PetDTO petDTO = petService.convertToPetDTO(pet);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable("id") Long id) {
        petService.deletePetById(id);

        return ResponseEntity.
                status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable("id") Long id, @RequestBody @Valid Pet pet) {
        Pet updatedPet = petService.updatePet(id, pet);
        PetDTO petDTO = petService.convertToPetDTO(updatedPet);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(petDTO);
    }
}
