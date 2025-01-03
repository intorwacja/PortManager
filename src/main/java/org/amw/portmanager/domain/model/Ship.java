package org.amw.portmanager.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.amw.portmanager.domain.ShipType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String imoNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ShipType shipType;

    @Column(nullable = false)
    private double capacity;

    private double length;
    private double width;
    private double height;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Port port;

    @OneToOne
    private Route route;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private double draft;
}
