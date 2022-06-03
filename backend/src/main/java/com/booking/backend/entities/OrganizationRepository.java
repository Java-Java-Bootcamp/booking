package com.booking.backend.entities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findAllByName(String name);

    Page<Organization> findAllByTypeOrganization(Pageable page, TypeOrganization typeOrganization);
}
