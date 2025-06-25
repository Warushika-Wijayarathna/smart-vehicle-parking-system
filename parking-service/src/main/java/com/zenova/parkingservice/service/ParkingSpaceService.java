package com.zenova.parkingservice.service;


import com.zenova.parkingservice.dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingSpaceService {
    List<ParkingSpaceDTO> getAllSpaces();
    ParkingSpaceDTO getSpaceById(Long id);
    ParkingSpaceDTO createSpace(ParkingSpaceDTO spaceDTO);
    void deleteSpace(Long id);

    List<ParkingSpaceDTO> getAvailableSpace();

    ParkingSpaceDTO updateSpace(Long id, ParkingSpaceDTO spaceDTO);
}
