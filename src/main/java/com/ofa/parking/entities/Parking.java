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
    private String phone;
    private double lat;
    private double lon;
    private double price;
    @Transient
    private int capacity;
    @OneToMany(mappedBy = "parking")
    private List<ParkingSlot> parkingSlots= new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Vehicule parkingType;
    @ElementCollection
    List<String> images = new ArrayList<String>();
    public int getCapacity() {
        return parkingSlots.size();
    }
}
