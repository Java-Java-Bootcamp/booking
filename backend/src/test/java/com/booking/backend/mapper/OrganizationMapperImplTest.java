package com.booking.backend.mapper;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
import com.booking.backend.entity.TypeOrganization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationMapperImplTest {

    private OrganizationMapperImpl organizationMapper = new OrganizationMapperImpl();
    private Organization organizationEx = new Organization(1L, "Org", "10-22", 100.4, 8.7, TypeOrganization.BAR);
    private OrganizationDto organizationDtoEx = new OrganizationDto(1L, "Org", "10-22", 100.4, 8.7, "Bar");


    @Test
    void convertFromOrganizationToOrganizationDto() {
        assertEquals(organizationDtoEx, organizationMapper.convertFromOrganizationToOrganizationDto(organizationEx));
    }

    @Test
    void convertFromOrganizationDtoToOrganization() {
        assertEquals(organizationEx, organizationMapper.convertFromOrganizationDtoToOrganization(organizationDtoEx));
    }
}