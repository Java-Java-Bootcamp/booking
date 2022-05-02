package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
import com.booking.backend.entity.TypeOrganization;
import com.booking.backend.mapper.OrganizationMapper;
import com.booking.backend.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public Page<OrganizationDto> getAllOrganizations(Pageable pageable) {
        return organizationRepository.findAll(pageable)
                .map(organizationMapper::convertFromOrganizationToOrganizationDto);
    }

    public Page<OrganizationDto> getOrganizationsByType(Pageable pageable, TypeOrganization type) {
        return organizationRepository.findAllByTypeOrganization(pageable, type)
                .map(organizationMapper::convertFromOrganizationToOrganizationDto);
    }

    @Override
    public List<OrganizationDto> findAllByName(String name) {
        return organizationRepository.findAllByName(name).stream()
                .map(organizationMapper::convertFromOrganizationToOrganizationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(organizationMapper::convertFromOrganizationToOrganizationDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrganization(OrganizationDto organizationDto) {
        organizationRepository.save(organizationMapper.convertFromOrganizationDtoToOrganization(organizationDto));
    }

    @Override
    public List<TypeOrganization> getAllTypesOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(Organization::getTypeOrganization)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto getById(Long id) {
        return organizationMapper.convertFromOrganizationToOrganizationDto(organizationRepository.getById(id));
    }
}

