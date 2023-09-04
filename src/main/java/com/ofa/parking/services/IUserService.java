package com.ofa.parking.services;

import com.ofa.parking.dtos.*;

public interface IUserService {
    UserDto addUser(RegisterDto user);
    Boolean changePassword(ChangePasswordDto changePassword);
    boolean reserve(Long userId, Long parkingSlotId, ReservationDto reservationDto);
    UserDto login(LoginDto loginDto);
}
