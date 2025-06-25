package com.zenova.parkingspaceservice.service.impl;

import com.zenova.parkingspaceservice.dto.ParkingZoneDTO;
import com.zenova.parkingspaceservice.entity.ParkingZone;
import com.zenova.parkingspaceservice.repository.ParkingZoneRepository;
import com.zenova.parkingspaceservice.service.ParkingZoneService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingZoneServiceImpl implements ParkingZoneService {

    private final ParkingZoneRepository parkingZoneRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://user-service/api/user/";

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
        validateOwner(zoneDTO.getOwnerId());
        ParkingZone zone = modelMapper.map(zoneDTO, ParkingZone.class);
        ParkingZone savedZone = parkingZoneRepository.save(zone);
        return modelMapper.map(savedZone, ParkingZoneDTO.class);
    }

    @Override
    public void deleteZone(Long id) {
        parkingZoneRepository.deleteById(id);
    }

    private void validateOwner(Long ownerId) {
        String url = USER_SERVICE_URL + ownerId;
        ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("Owner with ID " + ownerId + " does not exist in user-service.");
        }
    }
}
