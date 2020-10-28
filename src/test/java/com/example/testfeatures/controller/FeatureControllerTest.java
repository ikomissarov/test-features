package com.example.testfeatures.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FeatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get("/features/08a190bf-8c7e-4e94-a22c-7f3be11f642c"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id", is("08a190bf-8c7e-4e94-a22c-7f3be11f642c")))
            .andExpect(jsonPath("timestamp", is(1555044772083L)))
            .andExpect(jsonPath("beginViewingDate", is(1555044772083L)))
            .andExpect(jsonPath("endViewingDate", is(1555044797082L)))
            .andExpect(jsonPath("missionName", is("Sentinel-1A")));
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get("/features/abc"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/features"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(14)));
    }

    @Test
    void testGetQuicklook() throws Exception {
        mockMvc.perform(get("/features/08a190bf-8c7e-4e94-a22c-7f3be11f642c/quicklook"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.IMAGE_PNG));
    }
}