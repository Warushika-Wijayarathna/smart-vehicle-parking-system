package com.zenova.parkingspaceservice.dto;

import com.zenova.parkingspaceservice.util.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingSpaceDTO {
    private Long id;
    private ParkingZoneDTO zone;
    private String locationDescription;
    private boolean isAvailable;
    private Status status;
    private Long ownerId;
    private LocalDateTime lastUpdated;
}
