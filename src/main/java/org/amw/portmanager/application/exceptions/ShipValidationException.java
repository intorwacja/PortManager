package org.amw.portmanager.application.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ShipValidationException extends RuntimeException {
    private Map<String, String> violations = new HashMap<>();

    public ShipValidationException(Map<String, String> violations) {
        this.violations = violations;
    }

    public Map<String, String> getViolations() {
        return violations;
    }
}
