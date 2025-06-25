package com.zenova.parkingspaceservice.repository;

import com.zenova.parkingspaceservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
