package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;

import java.util.List;

public interface OrganizationService {

    List<OrganizationDto> getSortedOrganization(Integer pageNo, Integer pageSize, String sortBy);

    List<OrganizationDto> findAllByName(String name);

    List<OrganizationDto> getAll();

    void updateOrganization(OrganizationDto organizationDto);
}
