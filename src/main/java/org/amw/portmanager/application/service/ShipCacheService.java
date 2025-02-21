package org.amw.portmanager.application.service;

import org.amw.portmanager.domain.model.Ship;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ShipCacheService {

    @Cacheable(value = "notifiedShips", key = "#ship.id")
    public boolean isShipNotNotified(Ship ship) {
        return true;
    }

    @CachePut(value = "notifiedShips", key = "#ship.id")
    public boolean markAsNotified(Ship ship) {
        return false;
    }
}

