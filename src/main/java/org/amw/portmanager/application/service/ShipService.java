package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.validation.ship.ShipValidatorService;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.repository.ShipRepository;
import org.springframework.beans.BeanUtils;
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

    public Ship getShipByImoNumber(String imoNumber) {
        return shipRepository.findByImoNumber(imoNumber).orElseThrow();
    }

    public void deleteShipByImoNumber(String imoNumber) {
        shipRepository.findByImoNumber(imoNumber).ifPresent(shipRepository::delete);
    }

    public void updateShip(String imoNumber, Ship ship) {
        Ship actualShip = shipRepository.findByImoNumber(imoNumber).orElseThrow();

        BeanUtils.copyProperties(ship, actualShip, "id");

        shipRepository.save(actualShip);
    }
}
