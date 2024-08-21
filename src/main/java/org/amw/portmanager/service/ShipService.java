package org.amw.portmanager.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipService {
    private final ShipRepository shipRepository;

    public void addShip(Ship ship) {
        shipRepository.save(ship);
    }

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }
}
