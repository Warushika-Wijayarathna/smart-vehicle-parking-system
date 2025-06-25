package com.zenova.parkingspaceservice.controller;

import com.zenova.parkingspaceservice.dto.ParkingZoneDTO;
import com.zenova.parkingspaceservice.service.ParkingZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-zone")
@RequiredArgsConstructor
public class ParkingZoneController {

    private final ParkingZoneService parkingZoneService;

    @GetMapping
    public ResponseEntity<List<ParkingZoneDTO>> getAllZones() {
        return ResponseEntity.ok(parkingZoneService.getAllZones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingZoneDTO> getZoneById(@PathVariable Long id) {
        ParkingZoneDTO zone = parkingZoneService.getZoneById(id);
        if (zone == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(zone);
    }

    @PostMapping
    public ResponseEntity<ParkingZoneDTO> createZone(@RequestBody ParkingZoneDTO zoneDTO) {
        return ResponseEntity.ok(parkingZoneService.createZone(zoneDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        parkingZoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}
