package com.zenova.userservice.controller;

import com.zenova.userservice.dto.LoginDTO;
import com.zenova.userservice.dto.LoginHistoryDTO;
import com.zenova.userservice.dto.UserDTO;
import com.zenova.userservice.entity.User;
import com.zenova.userservice.service.LoginHistoryService;
import com.zenova.userservice.service.UserService;
import com.zenova.userservice.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginHistoryService loginHistoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Add methods for authentication, registration, etc.

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        System.out.println("Registering user: ///////////////////////////////////");
        // Encode the password before creating the user
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserDTO createdUser = userService.createUser(userDTO);

        if (createdUser != null) {
            LoginHistoryDTO loginHistoryDTO = new LoginHistoryDTO();
            loginHistoryDTO.setLoginTime(LocalDateTime.now());
            loginHistoryDTO.setUser(modelMapper.map(createdUser, User.class));

            LoginHistoryDTO loginHistory = loginHistoryService.createLoginHistory(loginHistoryDTO);

            if (loginHistory == null) {
                return ResponseEntity.status(500).body("Failed to create login history");
            }

            String token = jwtUtil.generateToken(createdUser);
            return ResponseEntity.ok(Map.of("user", createdUser, "token", token));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails = userService.loadUserByUsername(loginDTO.getEmail());

        if (!passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        UserDTO userDTO = userService.getUserByEmail(loginDTO.getEmail());
        String token = jwtUtil.generateToken(userDTO);

        return ResponseEntity.ok(Map.of("user", userDTO, "token", token));
    }

}
