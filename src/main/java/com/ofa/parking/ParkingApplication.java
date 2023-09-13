package com.ofa.parking;

import com.ofa.parking.entities.Parking;
import com.ofa.parking.entities.ParkingSlot;
import com.ofa.parking.entities.User;
import com.ofa.parking.entities.Vehicule;
import com.ofa.parking.repositories.IParkingRepository;
import com.ofa.parking.repositories.IParkingSlotRepository;
import com.ofa.parking.repositories.IUserRepository;
import com.ofa.parking.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ParkingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

    @Autowired
    private IParkingSlotRepository parkingSlotRepository;
    @Autowired
    private IParkingRepository parkingRepository;
    @Autowired
    private IUserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
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







        Parking parking = new Parking();
        parking.setName("Hay Hassani");
        parking.setAddr("Hay Hassani");
        parking.setLon(-7.696186);
        parking.setLat(33.558316);
        parking.setPrice(3);
        parking.setParkingType(Vehicule.CAR);


       Parking parking1 =    parkingRepository.save(parking);
       parkingSlot.setParking(parking1);
       parkingSlot1.setParking(parking1);
       parkingSlot2.setParking(parking1);
       parkingSlot3.setParking(parking1);
       parkingSlot4.setParking(parking1);
        parking1.getParkingSlots().add(parkingSlotRepository.save(parkingSlot));
        parking1.getParkingSlots().add(parkingSlotRepository.save(parkingSlot1));
        parking1.getParkingSlots().add(parkingSlotRepository.save(parkingSlot2));
        parking1.getParkingSlots().add(parkingSlotRepository.save(parkingSlot3));
        parking1.getParkingSlots().add(parkingSlotRepository.save(parkingSlot4));

        User mohamed = new User();
        mohamed.setEmail("mohamedbhajy23@gmail.com");
        mohamed.setPassword(passwordEncoder().encode("qwerty"));
        mohamed.setName("Bawliet");
        userRepository.save(mohamed);




    }
}
