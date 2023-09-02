package com.ofa.parking.dtos;

import com.ofa.parking.entities.ParkingArea;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ParkingSlotDto {
    private Long id;
    public int number;
    private boolean isEmpty;
    private ParkingAreaDto parkingArea;
}
