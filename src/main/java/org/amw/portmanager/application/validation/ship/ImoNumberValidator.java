package org.amw.portmanager.application.validation.ship;

import org.amw.portmanager.domain.model.Ship;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class ImoNumberValidator implements ShipValidator{

    @Override
    public Map<String, String> validate(Ship ship) {
        Map<String, String> errors = new HashMap<>();

        if(!ship.getImoNumber().startsWith("IMO") || ship.getImoNumber().length() != 10){
            errors.put("imoNumber", ship.getImoNumber());
        }

        return errors;
    }
}
