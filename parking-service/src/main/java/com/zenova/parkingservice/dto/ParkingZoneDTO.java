package com.zenova.parkingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParkingZoneDTO {
    private Long id;
    private String name;
    private String city;
    private String zoneCode;
    private Long ownerId;
}
