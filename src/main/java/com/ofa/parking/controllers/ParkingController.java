package com.ofa.parking.controllers;

import com.ofa.parking.dtos.ParkingDto;
import com.ofa.parking.dtos.ReservationDto;
import com.ofa.parking.entities.Vehicule;
import com.ofa.parking.services.IParkingService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
    @RequestMapping("/api/v1/parking")

public class ParkingController {
    private final IParkingService iParkingService;

    public ParkingController(IParkingService iParkingService) {
        this.iParkingService = iParkingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> getParkingById(@PathVariable Long id){
        return ResponseEntity.ok(iParkingService.getParkingById(id));
    }
    @GetMapping("/nearest")
    public List<ParkingDto> getNearestParking(@RequestParam double lat,@RequestParam double lon){
        List<ParkingDto> parkingDtos=iParkingService.getNearestParking(lon,lat);
    return parkingDtos;
    }
    @GetMapping("/filter")
    public List<ParkingDto> getNearestParking(@RequestParam(required = false) String addr,@RequestParam(required = false) String vehicule){
        System.out.println(vehicule);
        List<ParkingDto> parkingDtos=iParkingService.getParkingByAddressAndVehicule(addr,vehicule);
        return parkingDtos;
    }
    @PostMapping("/reserve")
    public ResponseEntity<String> createReservation(
            @RequestParam("userId") Long userId,
            @RequestParam("parkingId") Long parkingId,
            @RequestParam("startTime") Date startTime,
            @RequestParam("endTime") Date endTime,
            @RequestParam double price
    ) {
        try {
            iParkingService.createReservation(startTime, endTime, parkingId, userId,price);

            return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
