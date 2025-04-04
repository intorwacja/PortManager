package org.amw.portmanager.repository;

import org.amw.portmanager.domain.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShipRepository extends JpaRepository<Ship, UUID> {
    Optional<Ship> findByImoNumber(String imoNumber);
}
