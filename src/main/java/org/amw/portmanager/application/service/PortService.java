package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.domain.model.Port;
import org.amw.portmanager.repository.PortRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortService {
    private final PortRepository portRepository;

    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }

    public void addPort(Port port){
        portRepository.save(port);
    }
}
