package com.booking.backend.repository;

import com.booking.backend.entity.Organization;
import com.booking.backend.entity.TypeOrganization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void init() {
        organizationRepository.save(new Organization(null, "Org", "1-2", 2000.4, 8.7, TypeOrganization.BAR));
        organizationRepository.save(new Organization(null, "Org", "1-2", 2000.4, 8.7, TypeOrganization.BAR));

    }

    @Test
    void getAllByNameNotFound() {
        List<Organization> list = organizationRepository.findAllByName("Not existed");
        assertTrue(list.isEmpty());
    }

    @Test
    void getAllByNameFound() {
        List<Organization> list = organizationRepository.findAllByName("Org");
        assertEquals(2, list.size());
    }
}