package com.primespark.userservice.service;

import com.primespark.userservice.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
}

