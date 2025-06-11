package com.primespark.userservice.controller;

import com.primespark.userservice.dto.LoginDTO;
import com.primespark.userservice.dto.LoginHistoryDTO;
import com.primespark.userservice.dto.UserDTO;
import com.primespark.userservice.entity.User;
import com.primespark.userservice.service.LoginHistoryService;
import com.primespark.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    private LoginHistoryService loginHistoryService;
    private ModelMapper modelMapper;

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
    private ResponseEntity<UserDTO> loginUser(LoginDTO loginDTO) {
        return null;
    }
}
