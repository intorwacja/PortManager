package org.amw.portmanager.application.validation.route;

import org.amw.portmanager.domain.model.Route;

import java.util.Map;

@FunctionalInterface
interface RouteValidator {
    Map<String, String> validate(Route route);
}
