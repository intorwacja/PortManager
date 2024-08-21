package org.amw.portmanager;

import org.springframework.boot.SpringApplication;

public class TestPortManagerApplication {

    public static void main(String[] args) {
        SpringApplication.from(PortManagerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
