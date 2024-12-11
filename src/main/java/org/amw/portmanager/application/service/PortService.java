package org.amw.portmanager.application.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.amw.portmanager.domain.model.Port;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.repository.PortRepository;
import org.amw.portmanager.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortService {
    private final PortRepository portRepository;
    private final ShipRepository shipRepository;

    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }

    public void addPort(Port port) {
        portRepository.save(port);
    }

    public List<Ship> getAllShipsInPort(String code) {
        Port port = portRepository.findByCode(code).orElseThrow();

        return port.getShips();
    }

    public Port getPortByCode(String code) {
        return portRepository.findByCode(code).orElseThrow();
    }

    public void addShipToPort(Port port, String imoNumber) {
        JsonObject jsonObject = JsonParser.parseString(imoNumber).getAsJsonObject();
        imoNumber = jsonObject.get("imoNumber").getAsString();
        Ship ship = shipRepository.findByImoNumber(imoNumber).orElseThrow();
        port.getShips().add(ship);
        ship.setPort(port);
        shipRepository.save(ship);
        portRepository.save(port);
    }

    public void deleteShip(Port port, String imoNumber) {
        port.getShips().removeIf(ship -> ship.getImoNumber().equals(imoNumber));
        portRepository.save(port);
    }
}
