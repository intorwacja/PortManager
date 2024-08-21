package org.amw.portmanager.domain.dto;

import jakarta.persistence.Embeddable;

@Embeddable
public record Location(
        String country,
        String city,
        String zipCode
) {
}
