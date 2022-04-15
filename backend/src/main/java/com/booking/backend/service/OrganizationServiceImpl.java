package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
import com.booking.backend.mapper.Mapper;
import com.booking.backend.repository.OrganizationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private Mapper mapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, Mapper mapper) {
        this.organizationRepository = organizationRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OrganizationDto> getSortedOrganization(Integer pageNo, Integer pageSize, String sortBy) {

        if (sortBy.equals("rate")) {
            changeOrganizationToOrganizationDtoWithPaging(pageNo, pageSize, "rating");
        }
        if (sortBy.equals("bill")) {
            changeOrganizationToOrganizationDtoWithPaging(pageNo, pageSize, "averageCheck");
        }
        if (sortBy.equals("name")) {
            changeOrganizationToOrganizationDtoWithPaging(pageNo, pageSize, "name");
        }
        return null;
    }

    private List<OrganizationDto> changeOrganizationToOrganizationDtoWithPaging(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Organization> pagedResult = organizationRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent().stream()
                    .map(mapper::convertFromOrganizationToOrganizationDto)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<OrganizationDto> findAllByName(String name) {
        return organizationRepository.getAllByName(name).stream()
                .map(mapper::convertFromOrganizationToOrganizationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(mapper::convertFromOrganizationToOrganizationDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrganization(OrganizationDto organizationDto) {
        organizationRepository.save(mapper.convertFromOrganizationDtoToOrganization(organizationDto));
    }
}

