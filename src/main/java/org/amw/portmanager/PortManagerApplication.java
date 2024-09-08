package org.amw.portmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PortManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortManagerApplication.class, args);
    }
}
