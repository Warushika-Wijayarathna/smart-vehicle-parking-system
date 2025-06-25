package com.zenova.parkingservice.service.impl;

import com.zenova.parkingservice.dto.ParkingSpaceDTO;
import com.zenova.parkingservice.entity.ParkingSpace;
import com.zenova.parkingservice.repository.ParkingSpaceRepository;
import com.zenova.parkingservice.service.ParkingSpaceService;
import com.zenova.parkingservice.util.Status;
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
        try{
            ParkingSpace space = modelMapper.map(spaceDTO, ParkingSpace.class);
            ParkingSpace savedSpace = parkingSpaceRepository.save(space);
            return modelMapper.map(savedSpace, ParkingSpaceDTO.class);
        }
        catch (Exception e) {
            System.out.println("Error creating parking space: " + e.getMessage());
            // Log the exception if needed
            return null; // Return null or handle the error as appropriate
        }

    }

    @Override
    public void deleteSpace(Long id) {
        parkingSpaceRepository.deleteById(id);
    }

    @Override
    public List<ParkingSpaceDTO> getAvailableSpace() {

        return parkingSpaceRepository.findAll()
                .stream()
                .filter(space -> space.getStatus().equals(Status.AVAILABLE))
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());


    }

    @Override
    public ParkingSpaceDTO updateSpace(Long id, ParkingSpaceDTO spaceDTO) {

        ParkingSpace existingSpace = parkingSpaceRepository.findById(id).orElse(null);
        if (existingSpace == null) {
            return null;
        }
        modelMapper.map(spaceDTO, existingSpace);
        ParkingSpace updatedSpace = parkingSpaceRepository.save(existingSpace);
        return modelMapper.map(updatedSpace, ParkingSpaceDTO.class);
    }
}
