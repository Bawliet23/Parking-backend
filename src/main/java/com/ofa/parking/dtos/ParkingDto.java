package com.ofa.parking.dtos;

import com.ofa.parking.entities.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private Long id;
    private String name;
    private String addr;
    private double lat;
    private double lon;
    private double price;
    private Vehicule parkingType;
    private double distance;
}
