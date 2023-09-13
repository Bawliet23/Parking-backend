package com.ofa.parking.repositories;

import com.ofa.parking.entities.Parking;
import com.ofa.parking.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IParkingRepository extends JpaRepository<Parking,Long> {
    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.lat)) *" +
            " cos(radians(s.lon) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.lat))))";
    @Query("SELECT s FROM Parking s WHERE " + HAVERSINE_FORMULA + " < :distance ORDER BY "+ HAVERSINE_FORMULA + " DESC")
    List<Parking> findParkingsWithInDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distanceWithInKM);

    List<Parking> findByAddrContainingAndParkingType(String addr, Vehicule parkingType);

    @Query("SELECT p FROM Parking p WHERE " +
            "(:addr IS NULL OR p.addr LIKE CONCAT('%', :addr, '%')) AND " +
            "(:parkingType IS NULL OR p.parkingType = :parkingType)")
    List<Parking> findByAddrAndParkingTypeOptimized(String addr, Vehicule parkingType);
    List<Parking> findAllByAddrContaining(String addr);
}
