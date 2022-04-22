package com.booking.backend.controller;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
import com.booking.backend.mapper.OrganizationMapper;
import com.booking.backend.repository.OrganizationRepository;
import com.booking.backend.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                new Organization(null, "Al", "10-22", 1000.0, 8.9));
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
        Organization organization = new Organization(null, "Al", "10-22", 1000.0, 7.7);
        Organization organization1 = new Organization(null, "Al", "10-22", 1000.0, 8.0);
        List<Organization> list = List.of(organization, organization1);
        organizationRepository.saveAll(list);

        mockMvc.perform(get("/organization").param("pageNo", "0")
                        .param("pageSize", "2")
                        .param("sortBy", "rate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].rating", is(7.7)));
    }

    @Test
    void getAllOrganization() throws Exception {
        Organization organization = new Organization(null, "Al", "10-22", 1000.0, 7.7);
        Organization organization1 = new Organization(null, "Al", "10-22", 1000.0, 8.0);
        List<Organization> list = List.of(organization, organization1);
        organizationRepository.saveAll(list);

        mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void addNewBooking() throws Exception {
        OrganizationDto organization = new OrganizationDto(null, "Al", "10-22", 1000.0, 8.7);

        mockMvc.perform(post("/organization").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organization)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}