package com.ofa.parking.repositories;
import com.ofa.parking.entities.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingAreaRepository extends JpaRepository<ParkingArea,Long> {
}
