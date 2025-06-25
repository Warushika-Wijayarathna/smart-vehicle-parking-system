package com.zenova.parkingspaceservice.repository;

import com.zenova.parkingspaceservice.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Long> {
}
