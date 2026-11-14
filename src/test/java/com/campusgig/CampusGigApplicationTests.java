package com.campusgig;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class CampusGigApplicationTests {

    @Test
    void contextLoads() {
        // Verifies that the Spring Boot application context boots without errors
    }
}
