package com.campusgig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginPageLoads() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Sign In to Your Account")));
    }

    @Test
    void testRegisterPageLoads() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"))
                .andExpect(model().attributeExists("userDto"));
    }

    @Test
    void testSuccessfulStudentLoginRedirectsToStudentDashboard() throws Exception {
        mockMvc.perform(post("/login")
                .param("email", "aryan.student@campus.edu")
                .param("password", "password123")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/dashboard"));
    }

    @Test
    void testInvalidLoginShowsError() throws Exception {
        mockMvc.perform(post("/login")
                .param("email", "aryan.student@campus.edu")
                .param("password", "wrongpassword")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }
}
