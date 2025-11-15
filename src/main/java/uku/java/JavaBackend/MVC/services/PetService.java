package uku.java.JavaBackend.MVC.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uku.java.JavaBackend.MVC.dto.PetDTO;
import uku.java.JavaBackend.MVC.dto.UserDTO;
import uku.java.JavaBackend.MVC.models.Pet;
import uku.java.JavaBackend.MVC.models.User;

import java.util.*;

@Service
public class PetService {

    private final Map<Long, Pet> mapPets;
    private final UserService userService;
    private Long idCount;

    public PetService(@Lazy UserService userService) {
        this.userService = userService;
        this.mapPets = new HashMap<>();
        this.idCount = 0L;
    }

    public Pet createPet(Pet pet) {
        User user = userService.getUserById(pet.getUser());

        Pet createdPet = new Pet(++idCount, pet.getName(), user.getId());
        mapPets.put(createdPet.getId(), createdPet);
        userService.createPetForUser(createdPet);
        return createdPet;
    }

    public Pet getPetById(Long id) {
        Optional<Pet> pet = Optional.ofNullable(mapPets.get(id));
        if (pet.isEmpty()){
            throw new NoSuchElementException("Pet not found");
        }
        return pet.get();
    }

    public List<Pet> getAllPets() {
        return mapPets.values()
                .stream()
                .toList();
    }

    public void deletePetById(Long id) {
        Pet pet = getPetById(id);

        userService.deletePetForUser(pet.getUser(), id);
        mapPets.remove(id);
    }

    public Pet updatePet(Long id, Pet pet) {
        Pet findedPet = getPetById(id);

        Pet updatedPet = new Pet(id, pet.getName(), pet.getUser());
        userService.updatePetForUser(pet.getUser(), findedPet, updatedPet);
        mapPets.put(id, updatedPet);
        return updatedPet;
    }

    public PetDTO convertToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();

        petDTO.setName(pet.getName());
        petDTO.setUser(pet.getUser());
        return petDTO;
    }

    public Pet convertToPet(PetDTO petDTO) {
        Pet pet = new Pet();

        pet.setName(petDTO.getName());
        pet.setUser(petDTO.getUser());
        return pet;
    }
}
