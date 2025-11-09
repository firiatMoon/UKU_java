package uku.java.JavaBackend.MVC.services;

import org.springframework.stereotype.Service;
import uku.java.JavaBackend.MVC.models.PetDTO;
import uku.java.JavaBackend.MVC.models.UserDTO;

import java.util.*;

@Service
public class UserService {

    private final Map<Long, UserDTO> mapUsers;
    private final PetService petService;
    private Long idCount;

    public UserService(PetService petService) {
        this.petService = petService;
        this.mapUsers = new HashMap<>();
        this.idCount = 0L;
    }

    public UserDTO createUser(UserDTO user) {
        UserDTO createdUser = new UserDTO(++idCount, user.getName(), user.getEmail(), user.getAge(), new ArrayList<>());
        mapUsers.put(createdUser.getId(), createdUser);
        return createdUser;
    }

    public UserDTO getUserById(Long id) {
        return mapUsers.get(id);
    }

    public void createPetForUser(PetDTO pet) {
        Optional<UserDTO> user = Optional.ofNullable(getUserById(pet.getUser()));
        if(user.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        user.get().getPets().add(pet);
    }

    public void deleteUserById(Long id) {
        Optional<UserDTO> user = Optional.ofNullable(getUserById(id));
        if(user.isEmpty()){
            throw new NoSuchElementException("User not found");
        }

        user.get().getPets()
                .stream()
                .map(PetDTO::getId)
                .forEach(petService::deletePetById);

        mapUsers.remove(id);
    }

    public void deletePetForUser(Long userId, Long petId) {
        Optional<UserDTO> user = Optional.ofNullable(getUserById(userId));
        if(user.isEmpty()){
            throw new NoSuchElementException("User not found");
        }
        UserDTO updatedUser = user.get();

        List<PetDTO> updatedPets = updatedUser.getPets()
                .stream()
                .filter(pet -> !pet.getId().equals(petId))
                .toList();

        updatedUser.setPets(updatedPets);
    }

    public UserDTO updateUser(Long id, UserDTO user) {
        Optional<UserDTO> optionalUser = Optional.ofNullable(getUserById(id));
        if(optionalUser.isEmpty()){
            throw new NoSuchElementException("User not found");
        }
        UserDTO updatedUser = new UserDTO(id, user.getName(), user.getEmail(), user.getAge(), user.getPets());

        if(Objects.isNull(updatedUser.getPets())) {
            updatedUser.setPets(optionalUser.get().getPets());
        }
        mapUsers.put(id, updatedUser);
        return updatedUser;
    }

    public void updatePetForUser(Long user, PetDTO oldValuePet, PetDTO updatedPet) {
        Optional<UserDTO> optionalUser = Optional.ofNullable(getUserById(user));
        if(optionalUser.isEmpty()){
            throw new NoSuchElementException("User not found");
        }
        optionalUser.get().getPets().remove(oldValuePet);
        optionalUser.get().getPets().add(updatedPet);
    }

    public List<UserDTO> getAllUsers() {
        return mapUsers.values()
                .stream()
                .toList();
    }
}
