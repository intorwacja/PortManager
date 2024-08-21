package org.portmanager.portmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PortManagerApplicationTests {

    @Test
    void contextLoads() {
        //Context loads
    }

}
