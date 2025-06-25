// Updated ParkingSpace.java
package com.zenova.parkingservice.entity;

import com.zenova.parkingservice.util.Status;
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
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
}
