package com.zenova.parkingservice.service.impl;


import com.zenova.parkingservice.dto.ParkingZoneDTO;
import com.zenova.parkingservice.entity.ParkingZone;
import com.zenova.parkingservice.repository.ParkingZoneRepository;
import com.zenova.parkingservice.service.ParkingZoneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingZoneServiceImpl implements ParkingZoneService {

    private final ParkingZoneRepository parkingZoneRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ParkingZoneDTO> getAllZones() {
        return parkingZoneRepository.findAll()
                .stream()
                .map(zone -> modelMapper.map(zone, ParkingZoneDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParkingZoneDTO getZoneById(Long id) {
        ParkingZone zone = parkingZoneRepository.findById(id).orElse(null);
        return zone != null ? modelMapper.map(zone, ParkingZoneDTO.class) : null;
    }

    @Override
    public ParkingZoneDTO createZone(ParkingZoneDTO zoneDTO) {
        try {
            ParkingZone zone = modelMapper.map(zoneDTO, ParkingZone.class);
            ParkingZone savedZone = parkingZoneRepository.save(zone);
            return modelMapper.map(savedZone, ParkingZoneDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteZone(Long id) {
        parkingZoneRepository.deleteById(id);
    }
}
