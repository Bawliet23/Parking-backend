package com.ofa.parking.services;

import com.ofa.parking.dtos.ParkingDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IParkingService {
    public List<ParkingDto> getNearestParking(double Lon,double lat);
    public ParkingDto getParkingById(Long id);
    public List<ParkingDto> getParkingByAddress(String addr);

}
