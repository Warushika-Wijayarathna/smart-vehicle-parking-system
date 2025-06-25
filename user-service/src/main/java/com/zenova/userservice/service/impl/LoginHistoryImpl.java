package com.zenova.userservice.service.impl;

import com.zenova.userservice.dto.LoginHistoryDTO;
import com.zenova.userservice.entity.LoginHistory;
import com.zenova.userservice.repository.LoginHistoryRepository;
import com.zenova.userservice.service.LoginHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginHistoryImpl implements LoginHistoryService {

    private LoginHistoryRepository loginHistoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LoginHistoryImpl(LoginHistoryRepository loginHistoryRepository, ModelMapper modelMapper) {
        this.loginHistoryRepository = loginHistoryRepository;
        this.modelMapper = modelMapper;
    }

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
