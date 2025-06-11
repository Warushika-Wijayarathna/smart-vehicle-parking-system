package com.primespark.userservice.service;

import com.primespark.userservice.dto.LoginHistoryDTO;
import com.primespark.userservice.dto.UserDTO;

import java.util.List;

public interface LoginHistoryService {
    LoginHistoryDTO createLoginHistory(LoginHistoryDTO loginHistoryDTO);
    List<LoginHistoryDTO> getAllLoginHistory();
}
