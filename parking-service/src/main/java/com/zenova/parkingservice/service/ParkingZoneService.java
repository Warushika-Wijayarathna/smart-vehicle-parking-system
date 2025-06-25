package com.zenova.parkingservice.service;


import com.zenova.parkingservice.dto.ParkingZoneDTO;

import java.util.List;

public interface ParkingZoneService {
    List<ParkingZoneDTO> getAllZones();
    ParkingZoneDTO getZoneById(Long id);
    ParkingZoneDTO createZone(ParkingZoneDTO zoneDTO);
    void deleteZone(Long id);
}
