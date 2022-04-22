package com.booking.backend.repository;

import com.booking.backend.entity.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void init() {
        organizationRepository.save(new Organization(null, "Org", "1-2", 2000.4, 8.7));
        organizationRepository.save(new Organization(null, "Org", "1-2", 2000.4, 8.7));

    }

    @Test
    void getAllByNameNotFound() {
        List<Organization> list = organizationRepository.getAllByName("Not existed");
        assertTrue(list.isEmpty());
    }

    @Test
    void getAllByNameFound() {
        List<Organization> list = organizationRepository.getAllByName("Org");
        assertEquals(2, list.size());
    }
}