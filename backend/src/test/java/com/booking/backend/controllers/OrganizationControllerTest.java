package com.booking.backend.controllers;

import com.booking.backend.entities.Organization;
import com.booking.backend.entities.TypeOrganization;
import com.booking.backend.entities.OrganizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizationControllerTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        organizationRepository.save(
                new Organization(null, "Al", "10-22", 1000.0, 8.9, TypeOrganization.BAR));
    }

    @AfterEach
    void destroy() {
        organizationRepository.deleteAll();
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Al")));
    }

    @Test
    void getAllWithPage() throws Exception {
        Organization organization = new Organization(null, "Al", "10-22", 1000.0, 7.7, TypeOrganization.BAR);
        Organization organization1 = new Organization(null, "Al", "10-22", 1000.0, 8.0, TypeOrganization.BAR);
        List<Organization> list = List.of(organization, organization1);
        organizationRepository.saveAll(list);

        mockMvc.perform(get("/organization").param("pageNo", "0")
                        .param("pageSize", "2")
                        .param("sortBy", "rate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].rating", is(7.7)));
    }

    @Test
    void getAllOrganization() throws Exception {
        Organization organization = new Organization(null, "Al", "10-22", 1000.0, 7.7, TypeOrganization.BAR);
        Organization organization1 = new Organization(null, "Al", "10-22", 1000.0, 8.0, TypeOrganization.BAR);
        List<Organization> list = List.of(organization, organization1);
        organizationRepository.saveAll(list);

        mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}