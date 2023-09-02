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
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Transient
    private int slotAvailable;
    @ManyToOne
    private  Parking parking;
    @OneToMany(mappedBy = "floor")
    List<ParkingArea> parkingAreas = new ArrayList<>();
}
