package com.zenova.parkingspaceservice.service.impl;

import com.zenova.parkingspaceservice.dto.ParkingSpaceDTO;
import com.zenova.parkingspaceservice.entity.ParkingSpace;
import com.zenova.parkingspaceservice.repository.ParkingSpaceRepository;
import com.zenova.parkingspaceservice.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ParkingSpaceDTO> getAllSpaces() {
        return parkingSpaceRepository.findAll()
                .stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSpaceDTO getSpaceById(Long id) {
        ParkingSpace space = parkingSpaceRepository.findById(id).orElse(null);
        return space != null ? modelMapper.map(space, ParkingSpaceDTO.class) : null;
    }

    @Override
    public ParkingSpaceDTO createSpace(ParkingSpaceDTO spaceDTO) {
        ParkingSpace space = modelMapper.map(spaceDTO, ParkingSpace.class);
        ParkingSpace savedSpace = parkingSpaceRepository.save(space);
        return modelMapper.map(savedSpace, ParkingSpaceDTO.class);
    }

    @Override
    public void deleteSpace(Long id) {
        parkingSpaceRepository.deleteById(id);
    }
}
