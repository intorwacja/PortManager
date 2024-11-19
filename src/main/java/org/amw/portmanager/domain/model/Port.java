package org.amw.portmanager.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.amw.portmanager.domain.dto.Location;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    @Embedded
    private Location location;

    private int shipCapacity;

    @OneToMany
    private List<Ship> ships;

    private double maxDraft;
}
