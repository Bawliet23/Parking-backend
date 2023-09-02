package com.ofa.parking.repositories;

import com.ofa.parking.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFloorRepository extends JpaRepository<Floor,Long> {
}
