package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.dto.Mail;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.repository.ShipRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartureTimeScheduler {

    private final ShipRepository shipRepository;
    private final DateTimeService dateTimeService;
    private final SMTPMailService mailService;
    private final ShipCacheService shipCacheService;


    @Scheduled(fixedDelayString = "${notification.shipReminderCheckDelay}")
    private void shipDepartureNotification() {
        List<Ship> departureShips = findDepartureShips();
        departureShips.forEach(ship -> {
            mailService.sendMail(buildNotificationMail(ship));
            shipCacheService.markAsNotified(ship);
        });

    }

    private List<Ship> findDepartureShips() {
        return shipRepository.findAll().stream()
                .filter(ship -> ship.getDepartureTime() != null)
                .filter(shipCacheService::isShipNotNotified)
                .filter(ship -> dateTimeService.getCurrentDateTime().isAfter(ship.getDepartureTime().minusHours(2)))
                .toList();
    }

    private Mail buildNotificationMail(Ship ship) {
        return Mail.builder()
                .sender("sender@example.com")
                .receiver("recipient@example.com")
                .subject("Departure Time")
                .body("Ship departure time is close. Ship name: " + ship.getName())
                .build();
    }
}
