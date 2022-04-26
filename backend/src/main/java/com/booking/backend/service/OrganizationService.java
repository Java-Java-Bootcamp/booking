package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.TypeOrganization;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDto> getSortedOrganizationByType(Integer pageNo, Integer pageSize, String sortBy, TypeOrganization typeOrganization);

    List<OrganizationDto> getSortedOrganization(Integer pageNo, Integer pageSize, String sortBy);

    List<OrganizationDto> findAllByName(String name);

    List<OrganizationDto> getAll();

    OrganizationDto getById(Long id);

    void updateOrganization(OrganizationDto organizationDto);

    List<OrganizationDto> getByType(TypeOrganization type);
}
