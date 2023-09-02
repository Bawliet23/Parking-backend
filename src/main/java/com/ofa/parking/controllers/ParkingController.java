package com.ofa.parking.controllers;

import com.ofa.parking.dtos.ParkingDto;
import com.ofa.parking.services.IParkingService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking")
@Data
public class ParkingController {
    private IParkingService iParkingService;

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> getParkingById(@PathVariable Long id){
        return null;
    }

}
