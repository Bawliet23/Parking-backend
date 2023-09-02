package com.ofa.parking.services;

import com.ofa.parking.dtos.ParkingSlotDto;

public interface IParkingSlotService {
    ParkingSlotDto getParkingSlotById(Long id);
    ParkingSlotDto reserveParkingSlot(Long id);
    boolean cancelReservation(Long id);
}
