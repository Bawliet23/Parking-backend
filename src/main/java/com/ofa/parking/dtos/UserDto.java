package com.ofa.parking.dtos;

import com.ofa.parking.entities.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor  @NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;

    private List<ReservationDto> reservations = new ArrayList<>();
}
