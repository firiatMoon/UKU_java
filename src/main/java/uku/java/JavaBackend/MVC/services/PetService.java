package uku.java.JavaBackend.MVC.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uku.java.JavaBackend.MVC.models.PetDTO;

import java.util.*;

@Service
public class PetService {

    private final Map<Long, PetDTO> mapPets;
    private final UserService userService;
    private Long idCount;

    public PetService(@Lazy UserService userService) {
        this.userService = userService;
        this.mapPets = new HashMap<>();
        this.idCount = 0L;
    }

    public PetDTO createPet(PetDTO pet) {
        PetDTO createdPet = new PetDTO(++idCount, pet.getName(), pet.getUser());
        mapPets.put(createdPet.getId(), createdPet);
        userService.createPetForUser(createdPet);
        return createdPet;
    }

    public PetDTO getPetById(Long id) {
        return mapPets.get(id);
    }

    public List<PetDTO> getAllPets() {
        return mapPets.values()
                .stream()
                .toList();
    }

    public void deletePetById(Long id) {
        Optional<PetDTO> deletedPet = Optional.ofNullable(getPetById(id));
        if(deletedPet.isEmpty()){
            throw new NoSuchElementException("Pet not found");
        }
        userService.deletePetForUser(deletedPet.get().getUser(), id);
        mapPets.remove(id);
    }

    public PetDTO updatePet(Long id, PetDTO pet) {
        Optional<PetDTO> optionalPet = Optional.ofNullable(getPetById(id));
        if(optionalPet.isEmpty()){
            throw new NoSuchElementException("Pet not found");
        }
        PetDTO updatedPet = new PetDTO(id, pet.getName(), pet.getUser());
        userService.updatePetForUser(pet.getUser(), optionalPet.get(), updatedPet);
        mapPets.put(id, updatedPet);
        return updatedPet;
    }
}
