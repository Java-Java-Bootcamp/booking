package com.booking.backend.controller;


import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrganizationController {

    private OrganizationService organizationService;

    @GetMapping("/organization/pageNo={pageNo},pageSize={pageSize},sortBy={sortBy}")
    public List<OrganizationDto> getAll(@PathVariable Integer pageNo,
                                        @PathVariable Integer pageSize,
                                        @PathVariable String sortBy) {
        return organizationService.getSortedOrganization(pageNo, pageSize, sortBy);
    }

    @GetMapping("/organization/name={name}")
    public List<OrganizationDto> getAll(@PathVariable String name) {
        return organizationService.findAllByName(name);
    }

    @GetMapping("/organization/id={id}")
    public OrganizationDto getById(@PathVariable Long id) {
        return organizationService.getById(id);
    }

    @GetMapping("/organization")
    public List<OrganizationDto> getAllOrganization() {
        return organizationService.getAll();
    }

}
