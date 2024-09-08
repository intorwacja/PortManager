package org.amw.portmanager.repository;

import org.amw.portmanager.domain.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Integer> {

}
