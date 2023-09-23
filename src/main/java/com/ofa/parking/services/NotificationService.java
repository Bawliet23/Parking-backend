package com.ofa.parking.services;

import com.ofa.parking.entities.Reservation;
import com.ofa.parking.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotificationToUser(User user, Reservation reservation) {
        Date currentTime = new Date();
        Date endTime = reservation.getEndTime();
        long timeDifference = endTime.getTime() - currentTime.getTime();
        long fifteenMinutesInMillis = 15 * 60 * 1000;

        if (timeDifference > 0 && timeDifference <= fifteenMinutesInMillis) {

            String destination = "/notification";
            String message = "Your reservation is ending soon.";
            messagingTemplate.convertAndSend(destination, message);
        }
    }
}
