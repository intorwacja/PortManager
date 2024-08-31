package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.domain.model.Route;
import org.amw.portmanager.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public void addRoute(Route route) {
        routeRepository.save(route);
    }
}
