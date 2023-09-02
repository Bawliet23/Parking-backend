package com.ofa.parking.repositories;

import com.ofa.parking.entities.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingSlotRepository extends JpaRepository<ParkingSlot,Long> {
}
