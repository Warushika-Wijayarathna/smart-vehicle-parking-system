package com.zenova.parkingspaceservice.dto;

import lombok.Data;

@Data
public class ParkingZoneDTO {
    private Long id;
    private String name;
    private String city;
    private String zoneCode;
    private Long ownerId;
}
