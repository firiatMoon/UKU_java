package uku.java.JavaBackend.MVC.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uku.java.JavaBackend.MVC.models.UserDTO;
import uku.java.JavaBackend.MVC.services.*;

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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO user) {
        UserDTO createdUser = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDTO user) {
        UserDTO updatedUser = userService.updateUser(id, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);
    }
}
