package com.ofa.parking.dtos;

import com.ofa.parking.entities.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private Long id;
    private String name;
    private String addr;
    private String profile;
    private double lat;
    private double lon;
    private int capacity;
    private double price;
    private Vehicule parkingType;
    private double distance;

}
