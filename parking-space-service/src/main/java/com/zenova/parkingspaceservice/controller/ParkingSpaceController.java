package com.zenova.parkingspaceservice.controller;

import com.zenova.parkingspaceservice.dto.ParkingSpaceDTO;
import com.zenova.parkingspaceservice.service.ParkingSpaceService;
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
    public ResponseEntity<ParkingSpaceDTO> getSpaceById(@PathVariable Long id) {
        ParkingSpaceDTO space = parkingSpaceService.getSpaceById(id);
        if (space == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(space);
    }

    @PostMapping
    public ResponseEntity<ParkingSpaceDTO> createSpace(@RequestBody ParkingSpaceDTO spaceDTO) {
        return ResponseEntity.ok(parkingSpaceService.createSpace(spaceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        parkingSpaceService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }
}
