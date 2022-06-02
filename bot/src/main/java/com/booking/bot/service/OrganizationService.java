package com.booking.bot.service;

import com.booking.bot.infrastructure.OrganizationClient;
import com.booking.bot.view.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationClient organizationClient;

    public List<String> findAllTypes() {
        return organizationClient.findAllTypes();
    }

    public Page<OrganizationDto> findAllByType(String type, Integer page) {
        return organizationClient.findAllByType(type, page, Pageable.unpaged());
    }

    public OrganizationDto getById(String id) {
        return organizationClient.getById(Long.parseLong(id));
    }
}
