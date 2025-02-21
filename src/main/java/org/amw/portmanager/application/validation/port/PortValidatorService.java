package org.amw.portmanager.application.validation.port;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.exceptions.PortValidationException;
import org.amw.portmanager.domain.model.Port;
import org.amw.portmanager.domain.model.Ship;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortValidatorService {
    private final Collection<PortValidator> validators;

    public void validate(Port port, Ship ship) {
        Map<String, String> errors = validators.stream()
                .map(validator -> validator.validate(ship, port))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!errors.isEmpty()) {
            throw new PortValidationException(errors);
        }
    }

}
