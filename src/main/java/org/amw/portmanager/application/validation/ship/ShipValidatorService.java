package org.amw.portmanager.application.validation.ship;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.exceptions.ShipValidationException;
import org.amw.portmanager.domain.model.Ship;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipValidatorService {
    private final Collection<ShipValidator> validators;

    public void validate(Ship ship) {
        Map<String, String> errors = validators.stream()
                .map(validator -> validator.validate(ship))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!errors.isEmpty()) {
            throw new ShipValidationException(errors);
        }
    }
}
