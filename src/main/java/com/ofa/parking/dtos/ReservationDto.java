package com.ofa.parking.dtos;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor @Data @NoArgsConstructor
public class ReservationDto {
    private Long id;
    private Date startTime;
    private Date endTime;
    private boolean isCancelled;
}
