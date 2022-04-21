package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
import com.booking.backend.mapper.OrganizationMapper;
import com.booking.backend.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

    @Mock
    private OrganizationRepository organizationRepository;
    @Mock
    private OrganizationMapper organizationMapper;
    @InjectMocks
    private OrganizationServiceImpl organizationService;

    @Test
    void getSortedOrganization() {
        Page<Organization> page = Mockito.mock(Page.class);
        when(organizationRepository.findAll(any(Pageable.class))).thenReturn(page);
        organizationService.getSortedOrganization(1, 1, "rate");
        verify(organizationRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findAllByName() {
//        Organization organization = new Organization(1L, "Org", "10-22", 234.0, 8.7);
        organizationService.findAllByName("Org");
        verify(organizationRepository, times(1)).getAllByName("Org");
//        verify(organizationMapper, times(1)).convertFromOrganizationToOrganizationDto(organization);
    }

    @Test
    void getAll() {
        organizationService.getAll();
        verify(organizationRepository, times(1)).findAll();
    }

    @Test
    void updateOrganization() {
        OrganizationDto organizationDto = new OrganizationDto(1L, "Org", "10-22", 234.0, 8.7);
        organizationService.updateOrganization(organizationDto);
        organizationMapper.convertFromOrganizationDtoToOrganization(organizationDto);
        organizationRepository.save(any());
    }
}