package org.amw.portmanager.domain.model;

import jakarta.persistence.*;
import org.amw.portmanager.domain.dto.Location;

import java.util.UUID;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @AttributeOverride(name = "country", column = @Column(name = "start_country"))
    @AttributeOverride(name = "city", column = @Column(name = "start_city"))
    @AttributeOverride(name = "zipCode", column = @Column(name = "start_zipCode"))
    private Location startLocation;


    @AttributeOverride(name = "country", column = @Column(name = "end_country"))
    @AttributeOverride(name = "city", column = @Column(name = "end_city"))
    @AttributeOverride(name = "zipCode", column = @Column(name = "end_zipCode"))
    private Location endLocation;

    @OneToOne
    private Ship ship;
}
