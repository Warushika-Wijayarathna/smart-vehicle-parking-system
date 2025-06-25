package com.zenova.parkingspaceservice.service;


import com.zenova.parkingspaceservice.dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingSpaceService {
    List<ParkingSpaceDTO> getAllSpaces();
    ParkingSpaceDTO getSpaceById(Long id);
    ParkingSpaceDTO createSpace(ParkingSpaceDTO spaceDTO);
    void deleteSpace(Long id);
}
