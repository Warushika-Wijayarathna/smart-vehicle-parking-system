package com.zenova.userservice.controller;

import com.zenova.userservice.dto.UserDTO;
import com.zenova.userservice.service.UserService;
import com.zenova.userservice.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    // Retrieve the user by given id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader("Authorization") String token,
                                               @PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Update the user by given id
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String token,
                                              @RequestBody UserDTO userDTO,
                                              @PathVariable Long id) {
        // Implement update logic here if needed
        return ResponseEntity.status(501).build(); // Not implemented
    }

    // Get all the users
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestHeader("Authorization") String token) {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Delete the user by given id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token,
                                           @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Activate the user by given id
    @PutMapping("/activate/{id}")
    public ResponseEntity<UserDTO> activateUser(@RequestHeader("Authorization") String token,
                                                @PathVariable Long id) {
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
    public ResponseEntity<UserDTO> deactivateUser(@RequestHeader("Authorization") String token,
                                                  @PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setActive(false);
        UserDTO updatedUser = userService.createUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Get current user from token
    @GetMapping("/getByToken")
    public ResponseEntity<UserDTO> getUserByToken(@RequestHeader("Authorization") String token) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;

        // Extract email from JWT
        String email = jwtUtil.extractEmail(jwt);

        if (email == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDTO user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
