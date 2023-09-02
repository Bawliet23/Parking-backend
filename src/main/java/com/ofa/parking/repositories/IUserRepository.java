package com.ofa.parking.repositories;

import com.ofa.parking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
