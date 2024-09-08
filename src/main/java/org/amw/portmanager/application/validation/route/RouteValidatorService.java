package org.amw.portmanager.application.validation.route;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.exceptions.RouteValidationException;
import org.amw.portmanager.domain.model.Route;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteValidatorService {

    private final Collection<RouteValidator> routeValidators;

    public void validate(Route route) {
        Map<String, String> errors = routeValidators.stream()
                .map(validator -> validator.validate(route))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!errors.isEmpty()) {
            throw new RouteValidationException(errors);
        }
    }

}
