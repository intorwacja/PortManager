package org.amw.portmanager.application.exceptions;

import java.util.HashMap;
import java.util.Map;

public class PortValidationException extends RuntimeException{
    private Map<String, String> violations = new HashMap<>();

    public PortValidationException(Map<String, String> violations) {
        this.violations = violations;
    }

    public Map<String, String> getViolations() {
        return violations;
    }
}
