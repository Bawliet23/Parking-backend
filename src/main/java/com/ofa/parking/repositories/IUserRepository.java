package com.ofa.parking.repositories;

import com.ofa.parking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
   User findUserByEmail(String email);
}
