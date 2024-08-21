package org.amw.portmanager.web.controller;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ships")
@RequiredArgsConstructor
public class ShipController {

    private final ShipService shipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addShip(@RequestBody Ship ship) {
        shipService.addShip(ship);
    }

    @GetMapping
    public List<Ship> getAllShips() {
        return shipService.getAllShips();
    }
}
