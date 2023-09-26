package com.ofa.parking.services;

import com.ofa.parking.dtos.ParkingDto;
import com.ofa.parking.dtos.ReservationDto;
import com.ofa.parking.entities.*;
import com.ofa.parking.repositories.IParkingRepository;
import com.ofa.parking.repositories.IParkingSlotRepository;
import com.ofa.parking.repositories.IReservationRepository;
import com.ofa.parking.repositories.IUserRepository;
import com.ofa.parking.utils.Distance;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingService implements IParkingService{

    private IParkingRepository iParkingRepository;
    private IParkingSlotRepository parkingSlotRepository;
    private IUserRepository userRepository;
    private IReservationRepository reservationRepository;
    private ModelMapper modelMapper;
    @Override
    public List<ParkingDto> getNearestParking(double lon, double lat) {
        List<Parking> parkings = iParkingRepository.findParkingsWithInDistance(lat,lon,2.5);
        List<ParkingDto> parkingDtos = parkings.stream().map(parking -> modelMapper.map(parking, ParkingDto.class)).toList();
        parkingDtos.forEach(parkingDto -> parkingDto.setDistance(Distance.calculateDistance(lat,lon,parkingDto.getLat(),parkingDto.getLon())));
        return parkingDtos;

    }

    @Override
    public ParkingDto getParkingById(Long id) {
        Optional<Parking> optionalParking = iParkingRepository.findById(id);
        if (optionalParking.isEmpty()) return null;
        return modelMapper.map(optionalParking.get(),ParkingDto.class);

    }

    @Override
    public List<ParkingDto> getParkingByAddressAndVehicule(String addr, String vehicule,double lon, double lat){

        Vehicule v = null;
        if (Objects.equals(vehicule, "CAR")) {
            v=Vehicule.CAR;
        }else if (Objects.equals(vehicule, "MOTO")){
            v=Vehicule.MOTO;
        }else if (Objects.equals(vehicule, "TRACK")){
            v=Vehicule.TRACK;
        }else if (Objects.equals(vehicule, "BIKE")){
            v=Vehicule.BIKE;
        }
        List<Parking> parkings = null;
        if (v!=null && addr!=null)
        {
            System.out.println("1111");
            parkings=iParkingRepository.findByAddrContainingAndParkingType(addr,v);
        }
        else
        {
            System.out.println("2222");
            parkings = iParkingRepository.findByAddrAndParkingTypeOptimized(addr,v);
        }

        List<ParkingDto> parkingDtos = parkings.stream().map(parking -> modelMapper.map(parking,ParkingDto.class)).collect(Collectors.toList());
        parkingDtos.forEach(parkingDto ->  parkingDto.setDistance(Distance.calculateDistance(lat,lon,parkingDto.getLat(),parkingDto.getLon())));
        return parkingDtos;
     }

    @Override
    public ReservationDto createReservation(Date startTime, Date endTime, Long parkingId, Long userId,double price) {

        List<ParkingSlot> emptySlots = parkingSlotRepository.findEmptySlotsBetweenPeriods(startTime, endTime, parkingId);
        if (emptySlots.isEmpty()) {
            throw new IllegalArgumentException("No available parking slots within the specified time period.");
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user ID.");
        }

        Reservation reservation = new Reservation();
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setUser(user.get());

        ParkingSlot selectedSlot = emptySlots.get(0);
        reservation.setParkingSlot(selectedSlot);

        reservation.setPrice(price);

        reservation = reservationRepository.save(reservation);


        parkingSlotRepository.save(selectedSlot);

        return modelMapper.map(reservation,ReservationDto.class);
    }
}
