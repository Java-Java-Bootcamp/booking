package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.mapper.OrganizationMapper;
import com.booking.backend.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

    @Mock
    private OrganizationRepository organizationRepository;
    @Mock
    private OrganizationMapper organizationMapper;
    @InjectMocks
    private OrganizationServiceImpl organizationService;

    @Test
    void findAllByName() {
        organizationService.findAllByName("Org");
        verify(organizationRepository, times(1)).findAllByName("Org");
    }

    @Test
    void getAll() {
        organizationService.getAll();
        verify(organizationRepository, times(1)).findAll();
    }

    @Test
    void updateOrganization() {
        OrganizationDto organizationDto
                = new OrganizationDto(1L, "Org", "10-22", 234.0, 8.7, "Bar");
        organizationService.updateOrganization(organizationDto);
        organizationMapper.convert(organizationDto);
        organizationRepository.save(any());
    }
}