package com.zenova.userservice.controller;

import com.zenova.userservice.dto.UserDTO;
import com.zenova.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // Retrieve the user by given id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Update the user by given id
    @PutMapping("/update/{id}")
    private ResponseEntity<UserDTO> updateUser(UserDTO userDTO, @PathVariable Long id) {
        return null;
    }


    // Get all the user
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Delete the user by given id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Activate the user by given id
    @PutMapping("/activate/{id}")
    public ResponseEntity<UserDTO> activateUser(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setActive(true);
        UserDTO updatedUser = userService.createUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Deactivate the user by given id
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<UserDTO> deactivateUser(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setActive(false);
        UserDTO updatedUser = userService.createUser(user);
        return ResponseEntity.ok(updatedUser);
    }
}
