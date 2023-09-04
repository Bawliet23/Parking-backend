package com.ofa.parking.controllers;

import com.ofa.parking.dtos.ParkingDto;
import com.ofa.parking.services.IParkingService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")

public class ParkingController {
    private IParkingService iParkingService;

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

}
