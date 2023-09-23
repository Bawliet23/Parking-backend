package com.ofa.parking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private Long amount;
    private String currency;
}
