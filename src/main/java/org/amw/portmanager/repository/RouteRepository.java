package org.amw.portmanager.repository;

import org.amw.portmanager.domain.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
