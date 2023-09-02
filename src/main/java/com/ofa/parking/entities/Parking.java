package com.ofa.parking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String addr;
    private double lat;
    private double lon;
    private double price;
    @OneToMany(mappedBy = "parking")
    private List<Floor> floors= new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Vehicule parkingType;


}
