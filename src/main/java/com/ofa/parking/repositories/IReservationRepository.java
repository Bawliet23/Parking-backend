package com.ofa.parking.repositories;

import com.ofa.parking.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findReservationByParkingSlotIdAndStartTimeBetween(Long id, Date start,Date end);
    List<Reservation> findByUserIdOrderByIdDesc(Long id);
}
