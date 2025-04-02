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
//        imoNumber = imoNumber.substring(3);
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://api.vesselfinder.com/vessels?userkey="+ getVesselFinderApiKey()
//                        + "&imo=" + imoNumber))
//                .GET()
//                .build();
//
//        HttpResponse<String> response;
//
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException | InterruptedException e) {
//            throw new FetchVesselDataException(e.getMessage());
//        }
//
//        return response.body();

        return "[\n" +
                "    {\n" +
                "        \"AIS\": {\n" +
                "            \"MMSI\": 304491000,\n" +
                "            \"TIMESTAMP\": \"2017-08-11 11:15:15 UTC\",\n" +
                "            \"LATITUDE\": 54.5910,\n" +
                "            \"LONGITUDE\": 18.8998,\n" +
                "            \"COURSE\": 285.6,\n" +
                "            \"SPEED\": 14.0,\n" +
                "            \"HEADING\": 286,\n" +
                "            \"NAVSTAT\": 0,\n" +
                "            \"IMO\": 9175717,\n" +
                "            \"NAME\": \"MARENO\",\n" +
                "            \"CALLSIGN\": \"V2BC5\",\n" +
                "            \"TYPE\": 70,\n" +
                "            \"A\": 133,\n" +
                "            \"B\": 20,\n" +
                "            \"C\": 14,\n" +
                "            \"D\": 11,\n" +
                "            \"DRAUGHT\": 8.7,\n" +
                "            \"DESTINATION\": \"GDYNIA\",\n" +
                "            \"LOCODE\": \"PLGDY\",\n" +
                "            \"ETA_AIS\": \"08-12 03:00\",\n" +
                "            \"ETA\": \"2017-08-12 03:00:00\",\n" +
                "            \"SRC\": \"TER\",\n" +
                "            \"ZONE\": \"Baltic Sea\",\n" +
                "            \"ECA\": true,\n" +
                "            \"DISTANCE_REMAINING\": 5.0,\n" +
                "            \"ETA_PREDICTED\": \"2017-08-11 12:00:00\"\n" +
                "        }\n" +
                "    }\n" +
                "]";
    }
}
