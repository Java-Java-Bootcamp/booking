package com.booking.backend.usecases;

import com.booking.backend.entities.Organization;
import com.booking.backend.entities.OrganizationRepository;
import com.booking.backend.entities.TypeOrganization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public Page<OrganizationDto> getAllOrganizations(Pageable pageable) {
        return organizationRepository.findAll(pageable)
                .map(organizationMapper::convert);
    }

    public Page<OrganizationDto> getOrganizationsByType(Pageable pageable, TypeOrganization type) {
        return organizationRepository.findAllByTypeOrganization(pageable, type)
                .map(organizationMapper::convert);
    }

    public List<OrganizationDto> findAllByName(String name) {
        return organizationRepository.findAllByName(name).stream()
                .map(organizationMapper::convert)
                .toList();
    }

    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(organizationMapper::convert)
                .toList();
    }

    public void updateOrganization(OrganizationDto organizationDto) {
        organizationRepository.save(organizationMapper.convert(organizationDto));
    }

    public List<TypeOrganization> getAllTypesOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(Organization::getTypeOrganization)
                .distinct()
                .collect(Collectors.toList());
    }

    public OrganizationDto getById(Long id) {
        return organizationMapper.convert(organizationRepository.getById(id));
    }
}

