package com.ofa.parking.services;

import com.ofa.parking.dtos.ParkingDto;
import com.ofa.parking.entities.Parking;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IParkingService {
    public List<Parking> getNearestParking(double Lon,double lat);
    public ParkingDto getParkingById(Long id);
    public List<Parking> getParkingByAdress(String adddr);

}
