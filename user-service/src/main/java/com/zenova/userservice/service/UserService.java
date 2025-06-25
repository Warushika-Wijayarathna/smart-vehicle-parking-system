package com.zenova.userservice.service;

import com.zenova.userservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
    UserDetails loadUserByUsername(String email);
    UserDTO getUserByEmail(String email);
}

