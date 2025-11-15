package uku.java.JavaBackend.MVC.services;

import org.springframework.stereotype.Service;
import uku.java.JavaBackend.MVC.dto.PetDTO;
import uku.java.JavaBackend.MVC.dto.UserDTO;
import uku.java.JavaBackend.MVC.models.Pet;
import uku.java.JavaBackend.MVC.models.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Map<Long, User> mapUsers;
    private final PetService petService;
    private Long idCount;

    public UserService(PetService petService) {
        this.petService = petService;
        this.mapUsers = new HashMap<>();
        this.idCount = 0L;
    }

    public User createUser(User user) {
        User createdUser = new User(++idCount, user.getName(), user.getEmail(), user.getAge(), new ArrayList<>());
        mapUsers.put(createdUser.getId(), createdUser);
        return createdUser;
    }

    public User getUserById(Long id) {
        Optional<User> user = Optional.ofNullable(mapUsers.get(id));
        if (user.isEmpty()){
            throw new NoSuchElementException("User not found");
        }
        return user.get();
    }

    public void createPetForUser(Pet pet) {
        User user = getUserById(pet.getId());
        user.getPets().add(pet);
    }

    public void deleteUserById(Long id) {
        User user = getUserById(id);

        user.getPets()
                .stream()
                .map(Pet::getId)
                .forEach(petService::deletePetById);

        mapUsers.remove(id);
    }

    public void deletePetForUser(Long userId, Long petId) {
        User updatedUser = getUserById(userId);

        List<Pet> updatedPets = updatedUser.getPets()
                .stream()
                .filter(pet -> !pet.getId().equals(petId))
                .toList();

        updatedUser.setPets(updatedPets);
    }

    public User updateUser(Long id, User user) {
        User findedUser = getUserById(id);

        User updatedUser = new User(id, user.getName(), user.getEmail(), user.getAge(), user.getPets());

        if(Objects.isNull(updatedUser.getPets())) {
            updatedUser.setPets(findedUser.getPets());
        }
        mapUsers.put(id, updatedUser);
        return updatedUser;
    }

    public void updatePetForUser(Long user, Pet oldValuePet, Pet updatedPet) {
        Optional<User> optionalUser = Optional.ofNullable(getUserById(user));
        if(optionalUser.isEmpty()){
            throw new NoSuchElementException("User not found");
        }
        optionalUser.get().getPets().remove(oldValuePet);
        optionalUser.get().getPets().add(updatedPet);
    }

    public List<User> getAllUsers() {
        return mapUsers.values()
                .stream()
                .toList();
    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        List<Pet> pets = user.getPets();
        List<PetDTO> petsDTO = pets.stream().map(petService::convertToPetDTO).toList();

        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setPets(petsDTO);
        return userDTO;
    }

    public User convertToUser(UserDTO userDTO) {
        User user = new User();

        List<PetDTO> petsDTO = userDTO.getPets();
        List<Pet> pets = petsDTO.stream().map(petService::convertToPet).toList();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setPets(pets);
        return user;
    }
}
