package org.amw.portmanager.application.dto;

import org.amw.portmanager.domain.model.Location;
import org.amw.portmanager.domain.model.Ship;

import java.util.UUID;

public record routeRequest(
        UUID id,
        Location startLocation,
        Location endLocation,
        Ship ship) {
}
