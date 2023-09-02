package com.ofa.parking;

import com.ofa.parking.entities.*;
import com.ofa.parking.repositories.IFloorRepository;
import com.ofa.parking.repositories.IParkingAreaRepository;
import com.ofa.parking.repositories.IParkingRepository;
import com.ofa.parking.repositories.IParkingSlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ParkingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }
    @Autowired
    private IParkingAreaRepository parkingAreaRepository;
    @Autowired
    private IParkingSlotRepository parkingSlotRepository;
    @Autowired
    private IParkingRepository parkingRepository;
    @Autowired
    private IFloorRepository floorRepository;


    @Bean
    public ModelMapper getModel(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {


        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setEmpty(false);
        parkingSlot.setNumber(1);
        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setEmpty(true);
        parkingSlot1.setNumber(2);
        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setEmpty(false);
        parkingSlot2.setNumber(3);
        ParkingSlot parkingSlot3 = new ParkingSlot();
        parkingSlot3.setEmpty(true);
        parkingSlot3.setNumber(4);
        ParkingSlot parkingSlot4 = new ParkingSlot();
        parkingSlot4.setEmpty(true);
        parkingSlot4.setNumber(5);


        ParkingArea parkingArea = new ParkingArea();
        parkingArea.getParkingSlots().add(parkingSlot);
        parkingArea.getParkingSlots().add(parkingSlot1);
        parkingSlot.setParkingArea(parkingArea);
        parkingSlot1.setParkingArea(parkingArea);
        parkingArea.setName("A1");



        ParkingArea parkingArea1 = new ParkingArea();
        parkingArea1.getParkingSlots().add(parkingSlot2);
        parkingSlot2.setParkingArea(parkingArea1);
        parkingArea1.setName("A2");

        ParkingArea parkingArea2 = new ParkingArea();
        parkingArea2.getParkingSlots().add(parkingSlot3);
        parkingArea2.getParkingSlots().add(parkingSlot4);
        parkingSlot3.setParkingArea(parkingArea2);
        parkingSlot4.setParkingArea(parkingArea2);
        parkingArea2.setName("A3");

        Floor f1 = new Floor();
        f1.setName("Floor 1");
        f1.getParkingAreas().add(parkingArea);
        f1.getParkingAreas().add(parkingArea1);
        f1.getParkingAreas().add(parkingArea2);
        parkingArea.setFloor(f1);
        parkingArea2.setFloor(f1);
        parkingArea1.setFloor(f1);

        Parking parking = new Parking();
        parking.setName("Hay Hassani");
        parking.setAddr("Hay El Oulfa, Hay-hassani, 20230");
        parking.setLon(33.5739467);
        parking.setLat(33.5739467);
        parking.setPrice(3);
        parking.getFloors().add(f1);
        parking.setParkingType(Vehicule.CAR);
        f1.setParking(parking);
        parkingRepository.save(parking);
        floorRepository.save(f1);
        parkingAreaRepository.save(parkingArea1);
        parkingAreaRepository.save(parkingArea2);
        parkingAreaRepository.save(parkingArea);
        parkingSlotRepository.save(parkingSlot);
        parkingSlotRepository.save(parkingSlot1);
        parkingSlotRepository.save(parkingSlot2);
        parkingSlotRepository.save(parkingSlot3);
        parkingSlotRepository.save(parkingSlot4);







    }
}
