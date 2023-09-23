package com.ofa.parking.services;

import com.ofa.parking.dtos.*;
import com.ofa.parking.entities.Reservation;

import java.util.List;

public interface IUserService {
    UserDto addUser(RegisterDto user);
    Boolean changePassword(ChangePasswordDto changePassword);
    boolean reserve(Long userId, Long parkingSlotId, ReservationDto reservationDto);
    UserDto login(LoginDto loginDto);
    List<ReservationDto> getReservations(Long id);
}
