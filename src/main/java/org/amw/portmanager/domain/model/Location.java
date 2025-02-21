package org.amw.portmanager.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Location(
        String country,
        String city,
        String zipCode
) {
}
