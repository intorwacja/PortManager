package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.validation.ship.ShipValidatorService;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipService {
    private final ShipRepository shipRepository;
    private final ShipValidatorService shipValidatorService;

    public void addShip(Ship ship) {
        shipValidatorService.validate(ship);
        shipRepository.save(ship);
    }

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }
}
