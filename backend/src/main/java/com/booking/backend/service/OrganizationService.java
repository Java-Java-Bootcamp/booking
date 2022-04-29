package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.TypeOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrganizationService {
    Page<OrganizationDto> getOrganizationsByType(Pageable pageable, TypeOrganization typeOrganization);

    Page<OrganizationDto> getOrganizations(Pageable pageable);

    List<OrganizationDto> findAllByName(String name);

    List<OrganizationDto> getAll();

    OrganizationDto getById(Long id);

    void updateOrganization(OrganizationDto organizationDto);
}
