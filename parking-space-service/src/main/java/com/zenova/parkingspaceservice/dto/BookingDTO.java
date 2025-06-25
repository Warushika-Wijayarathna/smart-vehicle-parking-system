package com.zenova.parkingspaceservice.dto;

import com.zenova.parkingspaceservice.util.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;
    private Long userId;
    private ParkingSpaceDTO space;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private LocalDateTime createdAt;
}
