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

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public Page<OrganizationDto> getAllOrganizations(Pageable pageable) {
        return organizationRepository.findAll(pageable)
                .map(organizationMapper::convert);
    }

    public Page<OrganizationDto> getOrganizationsByType(Pageable pageable, TypeOrganization type) {
        return organizationRepository.findAllByTypeOrganization(pageable, type)
                .map(organizationMapper::convert);
    }

    @Override
    public List<OrganizationDto> findAllByName(String name) {
        return organizationRepository.findAllByName(name).stream()
                .map(organizationMapper::convert)
                .toList();
    }

    @Override
    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(organizationMapper::convert)
                .toList();
    }

    @Override
    public void updateOrganization(OrganizationDto organizationDto) {
        organizationRepository.save(organizationMapper.convert(organizationDto));
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
        return organizationMapper.convert(organizationRepository.getById(id));
    }
}

