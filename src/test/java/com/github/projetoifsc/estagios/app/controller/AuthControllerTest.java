package com.github.projetoifsc.estagios.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    private final String BASE_URL = "/api/v1/";

    @Autowired
    private MockMvc api;

    @Test
    void notLoggedIn_shouldNotSeeSecured() throws Exception {
        api.perform(get(BASE_URL + "/auth/test"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedIn_shouldNotSeeSecured() throws Exception {
        api.perform(get(BASE_URL + "auth/test"))
                .andExpect(status().isOk());
    }

    @Test
    void notLoggedIn_shouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get(BASE_URL + "/auth/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void simpleUser_shouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get(BASE_URL + "/auth/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAdminUser
    void adminUser_shouldSeeAdminEndpoint() throws Exception {
        api.perform(get(BASE_URL + "/auth/test"))
                .andExpect(status().isOk());
    }

}