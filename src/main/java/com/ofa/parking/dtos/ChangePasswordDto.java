package com.ofa.parking.dtos;

import lombok.Data;

@Data
public class ChangePasswordDto {
    Long id;
    String oldPassword;
    String newPassword;
}
