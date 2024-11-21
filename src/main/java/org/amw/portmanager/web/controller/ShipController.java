package org.amw.portmanager.web.controller;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.domain.ShipType;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.application.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    @ResponseStatus(HttpStatus.OK)
    public List<Ship> getAllShips() {
        return shipService.getAllShips();
    }

    @GetMapping("/{imoNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Ship getShipByImoNumber(@PathVariable("imoNumber") String imoNumber) {
        return shipService.getShipByImoNumber(imoNumber);
    }

    @DeleteMapping("/{imoNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShip(@PathVariable("imoNumber") String imoNumber) {
        shipService.deleteShipByImoNumber(imoNumber);
    }

    @GetMapping("/types")
    @ResponseStatus(HttpStatus.OK)
    public List<ShipType> getShipTypes() {
        return Arrays.stream(ShipType.values()).toList();
    }

    @PutMapping("/{imoNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShip(@PathVariable("imoNumber") String imoNumber, @RequestBody Ship ship){
        shipService.updateShip(imoNumber, ship);
    }

}
