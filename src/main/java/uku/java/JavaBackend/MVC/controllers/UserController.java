package uku.java.JavaBackend.MVC.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uku.java.JavaBackend.MVC.dto.PetDTO;
import uku.java.JavaBackend.MVC.dto.UserDTO;
import uku.java.JavaBackend.MVC.models.Pet;
import uku.java.JavaBackend.MVC.models.User;
import uku.java.JavaBackend.MVC.services.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> usersDTO = users.stream().map(userService::convertToUserDTO).toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usersDTO);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.createUser(user);
        UserDTO userDTO = userService.convertToUserDTO(createdUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = userService.convertToUserDTO(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody @Valid User user) {
        User updatedUser = userService.updateUser(id, user);
        UserDTO userDTO = userService.convertToUserDTO(updatedUser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTO);
    }
}
