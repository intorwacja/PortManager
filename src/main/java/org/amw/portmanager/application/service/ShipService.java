package org.amw.portmanager.application.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.exceptions.FetchVesselDataException;
import org.amw.portmanager.application.validation.ship.ShipValidatorService;
import org.amw.portmanager.domain.model.Port;
import org.amw.portmanager.domain.model.Ship;
import org.amw.portmanager.repository.PortRepository;
import org.amw.portmanager.repository.ShipRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class ShipService {
    private final ShipRepository shipRepository;
    private final ShipValidatorService shipValidatorService;
    private final PortRepository portRepository;
    HttpClient client = HttpClient.newHttpClient();

    @Value("${vesselfinder.api.key}")
    private String VesselFinderApiKey;

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

        String[] ignoredProperties = ship.getPort() == null ? new String[]{"id", "port"} : new String[]{"id"};
        BeanUtils.copyProperties(ship, actualShip, ignoredProperties);

        if (ship.getPort() != null) {
            actualShip.setPort(ship.getPort());
        }

        shipRepository.save(actualShip);
    }

    public void addPortToShip(String imoNumber, String portCode) {
        Ship ship = shipRepository.findByImoNumber(imoNumber).orElseThrow();
        Port port = portRepository.findByCode(portCode).orElseThrow();

        ship.setPort(port);

        shipRepository.save(ship);
    }

    public String getPortCodeOfShip(String imoNumber) {
        Ship ship = shipRepository.findByImoNumber(imoNumber).orElseThrow();
        return ship.getPort().getCode();
    }

    public String getShipLocation(String imoNumber) {
        imoNumber = imoNumber.substring(3);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.vesselfinder.com/vessels?userkey="+ getVesselFinderApiKey()
                        + "&imo=" + imoNumber))
                .GET()
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new FetchVesselDataException(e.getMessage());
        }

        return response.body();
    }
}
