package com.ofa.parking.repositories;

import com.ofa.parking.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation,Long> {
}
