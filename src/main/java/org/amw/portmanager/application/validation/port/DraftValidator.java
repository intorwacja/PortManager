package org.amw.portmanager.application.validation.port;

import org.amw.portmanager.domain.model.Port;
import org.amw.portmanager.domain.model.Ship;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class DraftValidator implements PortValidator {

    @Override
    public Map<String, String> validate(Ship ship, Port port) {

        Map<String, String> errors = new HashMap<>();

        if(ship.getDraft() > port.getMaxDraft()){
            errors.put("draft", "Draft is too high");
        }

        return errors;
    }
}
