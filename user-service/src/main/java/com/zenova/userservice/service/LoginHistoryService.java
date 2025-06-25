package com.zenova.userservice.service;

import com.zenova.userservice.dto.LoginHistoryDTO;

import java.util.List;

public interface LoginHistoryService {
    LoginHistoryDTO createLoginHistory(LoginHistoryDTO loginHistoryDTO);
    List<LoginHistoryDTO> getAllLoginHistory();
}
