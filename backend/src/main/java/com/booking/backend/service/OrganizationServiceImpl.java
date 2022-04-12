package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.Organization;
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

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<OrganizationDto> getSortedOrganization(Integer pageNo, Integer pageSize, String sortBy) {
        if (sortBy.equals("rate")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("rating"));
            Page<Organization> pagedResult = organizationRepository.findAll(paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent().stream()
                        .map(e -> new OrganizationDto(e.getId(),
                                e.getName(),
                                e.getSchedule(),
                                e.getNumbersOfTables(),
                                e.getAverageCheck(),
                                e.getRating(),
                                e.getReservations(),
                                e.getCuisine()))
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        }
        if (sortBy.equals("bill")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("averageCheck"));
            Page<Organization> pagedResult = organizationRepository.findAll(paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent().stream()
                        .map(e -> new OrganizationDto(e.getId(),
                                e.getName(),
                                e.getSchedule(),
                                e.getNumbersOfTables(),
                                e.getAverageCheck(),
                                e.getRating(),
                                e.getReservations(),
                                e.getCuisine()))
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        }
        if (sortBy.equals("name")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
            Page<Organization> pagedResult = organizationRepository.findAll(paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent().stream()
                        .map(e -> new OrganizationDto(e.getId(),
                                e.getName(),
                                e.getSchedule(),
                                e.getNumbersOfTables(),
                                e.getAverageCheck(),
                                e.getRating(),
                                e.getReservations(),
                                e.getCuisine()))
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        }
        return null;
    }

    @Override
    public List<OrganizationDto> findAllByName(String name) {
        return organizationRepository.getAllByName(name).stream()
                .map(e -> new OrganizationDto(e.getId(),
                        e.getName(),
                        e.getSchedule(),
                        e.getNumbersOfTables(),
                        e.getAverageCheck(),
                        e.getRating(),
                        e.getReservations(),
                        e.getCuisine()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(organization -> new OrganizationDto(organization.getId(),
                        organization.getName(),
                        organization.getSchedule(),
                        organization.getNumbersOfTables(),
                        organization.getAverageCheck(),
                        organization.getRating(),
                        organization.getReservations(),
                        organization.getCuisine()))
                .collect(Collectors.toList());
    }
}

