package org.amw.portmanager.application.validation.port;

import org.amw.portmanager.domain.model.Port;
import org.amw.portmanager.domain.model.Ship;

import java.util.Map;

@FunctionalInterface
interface PortValidator {
    Map<String, String> validate(Ship ship, Port port);
}
