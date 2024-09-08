package org.amw.portmanager.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.amw.portmanager.domain.dto.Location;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToOne
    private Ship ship;
}
