package com.ofa.parking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long lat;
    private Long lon;
    private String addr;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

}
