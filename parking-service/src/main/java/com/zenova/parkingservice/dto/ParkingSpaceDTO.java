package com.zenova.parkingservice.dto;

import com.zenova.parkingservice.util.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingSpaceDTO {
    private Long id;
    private ParkingZoneDTO zone;
    private String locationDescription;
    private Status status;
}
