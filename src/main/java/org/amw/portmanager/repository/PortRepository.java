package org.amw.portmanager.repository;

import org.amw.portmanager.domain.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortRepository extends JpaRepository<Port, Integer> {
    Optional<Port> findByCode(String code);
}
