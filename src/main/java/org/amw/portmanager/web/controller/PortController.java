package org.amw.portmanager.web.controller;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.service.PortService;
import org.amw.portmanager.domain.model.Port;
import org.amw.portmanager.domain.model.Ship;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ports")
@RequiredArgsConstructor
public class PortController {
    private final PortService portService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Port> getPorts() {
        return portService.getAllPorts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addPort(@RequestBody Port port) {
        portService.addPort(port);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{code}/ships")
    public List<Ship> getShipsInPort(@PathVariable String code) {
        return portService.getAllShipsInPort(code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{code}/addShip")
    public void addShipToPort(@PathVariable String code, @RequestParam String imoNumber) {
        Port port = portService.getPortByCode(code);
        portService.addShipToPort(port, imoNumber);
    }
}
