package com.zenova.parkingservice.repository;

import com.zenova.parkingservice.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Long> {
}
