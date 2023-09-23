package com.ofa.parking.services;

import com.ofa.parking.dtos.*;
import com.ofa.parking.entities.ParkingSlot;
import com.ofa.parking.entities.Reservation;
import com.ofa.parking.entities.User;
import com.ofa.parking.repositories.IParkingSlotRepository;
import com.ofa.parking.repositories.IReservationRepository;
import com.ofa.parking.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    private IUserRepository iUserRepository;
    private IParkingSlotRepository iParkingSlotRepository;
    private IReservationRepository iReservationRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    @Override
    public UserDto addUser(RegisterDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = iUserRepository.save(user);
        return modelMapper.map(u,UserDto.class);
    }

    @Override
    public Boolean changePassword(ChangePasswordDto changePassword) {
        Optional<User> user = iUserRepository.findById(changePassword.getId());
        boolean changed = false;
        if (user.isPresent()) {

            if ( passwordEncoder.matches(changePassword.getOldPassword(),user.get().getPassword())){
                user.get().setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
                iUserRepository.save(user.get());
                changed=true;
            }
        }
        return changed;
    }

    @Override
    public boolean reserve(Long userId, Long parkingSlotId,ReservationDto reservationDto) {
        Optional<User> user = iUserRepository.findById(userId);
        Optional<ParkingSlot> parkingSlot = iParkingSlotRepository.findById(parkingSlotId);
        if (user.isPresent() && parkingSlot.isPresent()) {
             Optional<Reservation> reservation = iReservationRepository.findReservationByParkingSlotIdAndStartTimeBetween(parkingSlotId,reservationDto.getStartTime(),reservationDto.getEndTime());
             if (reservation.isEmpty()){
                 Reservation reservation1 = new Reservation();
                 reservation1.setEndTime(reservationDto.getEndTime());
                 reservation1.setStartTime(reservationDto.getStartTime());
                 reservation1.setCancelled(false );
                 reservation1.setUser(user.get());
                 reservation1.setParkingSlot(parkingSlot.get());
                 iReservationRepository.save(reservation1);
               return true;
             }

        }
        return false;
    }

    @Override
    public UserDto login(LoginDto loginDto) {
        User user = iUserRepository.findUserByEmail(loginDto.getEmail());
        if (user!=null && passwordEncoder.matches(loginDto.getPassword(),user.getPassword()))
            return modelMapper.map(user,UserDto.class);
        return null;
    }

    @Override
    public List<ReservationDto> getReservations(Long id) {
        List<Reservation> reservations = iReservationRepository.findByUserIdOrderByIdDesc(id);
        return reservations.stream().map(reservation -> modelMapper.map(reservation,ReservationDto.class)).collect(Collectors.toList());
    }
}
