package com.primespark.userservice.controller;

import com.primespark.userservice.dto.LoginDTO;
import com.primespark.userservice.dto.LoginHistoryDTO;
import com.primespark.userservice.dto.UserDTO;
import com.primespark.userservice.entity.User;
import com.primespark.userservice.service.LoginHistoryService;
import com.primespark.userservice.service.UserService;
import com.primespark.userservice.util.JwtUtil;
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
    private LoginHistoryService loginHistoryService;
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Add methods for authentication, registration, etc.

    @PostMapping("/register")
    private ResponseEntity<UserDTO> registerUser(UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);

        if (createdUser != null) {
            LoginHistoryDTO loginHistoryDTO = new LoginHistoryDTO();
            loginHistoryDTO.setLoginTime(LocalDateTime.now());
            loginHistoryDTO.setUser(modelMapper.map(createdUser, User.class));

            LoginHistoryDTO loginHistory=loginHistoryService.createLoginHistory(loginHistoryDTO);

            if (loginHistory == null) {
                return ResponseEntity.status(500).body(null);
            }
        }
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails = userService.loadUserByUsername(loginDTO.getEmail());

        if (!passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        UserDTO userDTO = userService.getUserByEmail(loginDTO.getEmail());
        String token = jwtUtil.generateToken(userDTO);

        return ResponseEntity.ok(Map.of("token", token));
    }

}
