package org.amw.portmanager.application.validation.ship;

import org.amw.portmanager.domain.model.Ship;

import java.util.Map;

@FunctionalInterface
interface ShipValidator {
    Map<String, String> validate(Ship ship);
}
