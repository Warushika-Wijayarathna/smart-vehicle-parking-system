package com.zenova.parkingservice.controller;


import com.zenova.parkingservice.dto.ParkingSpaceDTO;
import com.zenova.parkingservice.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-space")
@RequiredArgsConstructor
public class ParkingSpaceController {

    private final ParkingSpaceService parkingSpaceService;

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getAllSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAllSpaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getSpaceById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        ParkingSpaceDTO space = parkingSpaceService.getSpaceById(id);
        if (space == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(space);
    }

    @PostMapping
    public ResponseEntity<ParkingSpaceDTO> createSpace(@RequestHeader("Authorization") String token, @RequestBody ParkingSpaceDTO spaceDTO) {
        ParkingSpaceDTO parkingSpaceDTO =parkingSpaceService.createSpace(spaceDTO);

        if (parkingSpaceDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(201).body(parkingSpaceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        parkingSpaceService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/availableSpace")
    public ResponseEntity<List<ParkingSpaceDTO>> getAvailableSpaces(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(parkingSpaceService.getAvailableSpace());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> updateSpace(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody ParkingSpaceDTO spaceDTO) {
        ParkingSpaceDTO updatedSpace = parkingSpaceService.updateSpace(id, spaceDTO);
        if (updatedSpace == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSpace);
    }

}
