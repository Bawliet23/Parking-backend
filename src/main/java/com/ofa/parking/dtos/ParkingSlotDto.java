package com.ofa.parking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ParkingSlotDto {
    private Long id;
    public int number;
    private boolean isEmpty;
}
