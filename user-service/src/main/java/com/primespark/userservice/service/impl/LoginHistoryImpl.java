package com.primespark.userservice.service.impl;

import com.primespark.userservice.dto.LoginHistoryDTO;
import com.primespark.userservice.entity.LoginHistory;
import com.primespark.userservice.repository.LoginHistoryRepository;
import com.primespark.userservice.service.LoginHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginHistoryImpl implements LoginHistoryService {

    private LoginHistoryRepository loginHistoryRepository;
    private ModelMapper modelMapper;

    @Override
    public LoginHistoryDTO createLoginHistory(LoginHistoryDTO loginHistoryDTO) {

        LoginHistory loginHistory = loginHistoryRepository.save(modelMapper.map(loginHistoryDTO, LoginHistory.class));

        return modelMapper.map(loginHistory, LoginHistoryDTO.class);
    }

    @Override
    public List<LoginHistoryDTO> getAllLoginHistory() {

        List<LoginHistory> loginHistories = loginHistoryRepository.findAll();

        return loginHistories.stream()
                .map(loginHistory -> modelMapper.map(loginHistory, LoginHistoryDTO.class))
                .toList();
    }
}
