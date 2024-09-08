package org.amw.portmanager.application.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class ClockBean {

    @Bean
     Clock getClock(){
        return Clock.systemDefaultZone();
    }
}
