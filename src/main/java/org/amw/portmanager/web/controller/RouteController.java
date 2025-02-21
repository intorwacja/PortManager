package org.amw.portmanager.web.controller;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.service.RouteService;
import org.amw.portmanager.domain.model.Route;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routes")
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoute(@RequestBody Route route) {
        routeService.addRoute(route);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }
}
