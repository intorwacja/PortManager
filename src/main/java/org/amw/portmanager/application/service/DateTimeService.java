package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DateTimeService {
    private final ClockBean clockBean;

    public LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now(clockBean.getClock());
    }
}
