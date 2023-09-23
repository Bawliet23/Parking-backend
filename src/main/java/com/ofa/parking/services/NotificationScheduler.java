package com.ofa.parking.services;

import com.ofa.parking.entities.Reservation;
import com.ofa.parking.entities.User;
import com.ofa.parking.repositories.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private IReservationRepository reservationRepository;

    @Scheduled(fixedRate = 60000)
    public void sendNotifications() {
        System.out.println("schedluer call");
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation : reservations) {
            User user = reservation.getUser();
            notificationService.sendNotificationToUser(user, reservation);
        }
    }
}
