package com.ofa.parking.services;

import com.ofa.parking.dtos.ParkingDto;
import com.ofa.parking.entities.Parking;
import com.ofa.parking.repositories.IParkingRepository;
import com.ofa.parking.utils.Distance;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingService implements IParkingService{

    private IParkingRepository iParkingRepository;
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
    public List<ParkingDto> getParkingByAddress(String addr) {
        List<Parking> parkings = iParkingRepository.findAllByAddrContaining(addr);
        return parkings.stream().map(parking -> modelMapper.map(parking,ParkingDto.class)).collect(Collectors.toList());
    }
}
