package org.amw.portmanager.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "port", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ship> ships;

    private double maxDraft;
}
