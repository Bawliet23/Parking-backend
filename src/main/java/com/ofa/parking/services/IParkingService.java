package com.ofa.parking.services;

import com.ofa.parking.dtos.ParkingDto;
import com.ofa.parking.dtos.ReservationDto;
import com.ofa.parking.entities.Reservation;
import com.ofa.parking.entities.Vehicule;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface IParkingService {
    public List<ParkingDto> getNearestParking(double Lon,double lat);
    public ParkingDto getParkingById(Long id);
    public List<ParkingDto> getParkingByAddressAndVehicule(String addr, String vehicule);
    public ReservationDto createReservation(Date startTime, Date endTime, Long parkingId, Long userId);


    }
