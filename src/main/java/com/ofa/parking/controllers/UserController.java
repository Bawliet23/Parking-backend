package com.ofa.parking.controllers;

import com.ofa.parking.dtos.LoginDto;
import com.ofa.parking.dtos.RegisterDto;
import com.ofa.parking.dtos.ReservationDto;
import com.ofa.parking.dtos.UserDto;
import com.ofa.parking.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private IUserService iUserService;
    @PostMapping(value = "/signUp",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody RegisterDto user) throws Exception {
        UserDto userDto = iUserService.addUser(user);
        if (user==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userDto);
    }
    @PostMapping(value = "/signIn",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sifnIn(@RequestBody LoginDto loginDto){
        UserDto userDto = iUserService.login(loginDto);
        if (userDto==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/{userId}/{parkingSlotId}")
    public ResponseEntity<?> reserveParkingSlot(@PathVariable Long userId , @PathVariable Long parkingSlotId , @RequestBody ReservationDto reservationDto) {
        boolean reservation = iUserService.reserve(userId,parkingSlotId,reservationDto);
        if(!reservation)
            return ResponseEntity.badRequest().body("Reservation Failed");
        return ResponseEntity.ok().body("Checkout successful");
    }
}
