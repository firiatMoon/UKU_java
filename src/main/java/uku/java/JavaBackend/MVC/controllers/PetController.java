package uku.java.JavaBackend.MVC.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uku.java.JavaBackend.MVC.models.PetDTO;
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
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(petService.getAllPets());
    }


    @PostMapping
    public ResponseEntity<PetDTO> createPet(@RequestBody @Valid PetDTO petDTO) {
        PetDTO createdPet = petService.createPet(petDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable("id") Long id) {
        PetDTO pet = petService.getPetById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable("id") Long id) {
        petService.deletePetById(id);
        return ResponseEntity.
                status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable("id") Long id, @RequestBody @Valid PetDTO petDTO) {
        PetDTO updatedPet = petService.updatePet(id, petDTO);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPet);
    }
}
