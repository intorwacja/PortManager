package org.amw.portmanager.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/maps")
public class MapsController {

    @Value("${google.maps.api.key}")
    private String apiKey;

    @GetMapping("/key")
    @ResponseStatus(HttpStatus.OK)
    public String getApiKey() {
        return apiKey;
    }
}
