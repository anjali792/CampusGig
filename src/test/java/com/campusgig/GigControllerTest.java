package com.campusgig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class GigControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomePageLoads() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testGigsListingPageLoads() throws Exception {
        mockMvc.perform(get("/gigs"))
                .andExpect(status().isOk())
                .andExpect(view().name("gigs/list"))
                .andExpect(model().attributeExists("gigs"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    void testGigDetailLoads() throws Exception {
        mockMvc.perform(get("/gigs/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("gigs/detail"))
                .andExpect(model().attributeExists("gig"));
    }
}
