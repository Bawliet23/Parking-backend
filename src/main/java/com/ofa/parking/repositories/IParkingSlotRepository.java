package com.ofa.parking.repositories;

import com.ofa.parking.entities.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IParkingSlotRepository extends JpaRepository<ParkingSlot,Long> {
    @Query("SELECT ps FROM ParkingSlot ps WHERE ps.parking.id = :id AND ps.id NOT IN (SELECT r.parkingSlot.id FROM Reservation r WHERE (:startTime < r.endTime) AND (:endTime > r.startTime))")
    List<ParkingSlot> findEmptySlotsBetweenPeriods(Date startTime, Date endTime,Long id);
}
