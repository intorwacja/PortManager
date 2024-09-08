package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.validation.route.RouteValidatorService;
import org.amw.portmanager.domain.model.Route;
import org.amw.portmanager.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteValidatorService routeValidatorService;

    public void addRoute(Route route) {
        routeValidatorService.validate(route);
        routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}
