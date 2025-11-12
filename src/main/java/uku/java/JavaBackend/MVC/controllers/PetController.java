package uku.java.JavaBackend.MVC.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(petService.getAllPets());
    }


    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody @Valid Pet pet) {
        Pet createdPet = petService.createPet(pet);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable("id") Long id) {
        Pet pet = petService.getPetById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable("id") Long id) {
        petService.deletePetById(id);
        return ResponseEntity.
                status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") Long id, @RequestBody @Valid Pet pet) {
        Pet updatedPet = petService.updatePet(id, pet);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPet);
    }
}
