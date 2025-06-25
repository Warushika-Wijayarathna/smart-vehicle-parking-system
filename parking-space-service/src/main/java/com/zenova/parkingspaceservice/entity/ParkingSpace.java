package com.zenova.parkingspaceservice.entity;

import com.zenova.parkingspaceservice.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ParkingZone zone;

    private String locationDescription;
    private boolean isAvailable = true;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    private Long ownerId;

    private LocalDateTime lastUpdated = LocalDateTime.now();


}

