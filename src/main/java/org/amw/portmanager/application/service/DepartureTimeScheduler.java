package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.dto.Mail;
import org.amw.portmanager.application.infrastructure.MailService;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.repository.ShipRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartureTimeScheduler {

    private final ShipRepository shipRepository;
    private  final DateTimeService dateTimeService;
    private final MailService mailService;


    @Scheduled(fixedDelayString = "${notification.shipReminderCheckDelay}")
    private void shipDepartureNotification() {
        List<Ship> departureShips = findDepartureShips();

        departureShips.forEach(ship -> mailService.sendMail(buildNotificationMail(ship)));
    }

    private List<Ship> findDepartureShips() {
        return shipRepository.findAll().stream()
                .filter(ship -> ship.getDepartureTime().isBefore(dateTimeService.getCurrentDateTime().plusHours(4)))
                .toList();
    }

    //TODO: Make mailing service asyc.
    private Mail buildNotificationMail(Ship ship) {     //TODO: Finish sending notifications after mailing service impl.
        return Mail.builder()
                .build();
    }
}
