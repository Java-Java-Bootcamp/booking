package com.booking.backend.controller;


import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.entity.TypeOrganization;
import com.booking.backend.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @RequestMapping(value = "/organization", params = {"pageNo", "pageSize", "sortBy"}, method = RequestMethod.GET)
    List<OrganizationDto> getSort(@RequestParam Integer pageNo,
                                  @RequestParam Integer pageSize,
                                  @RequestParam String sortBy) {
        return organizationService.getSortedOrganization(pageNo, pageSize, sortBy);
    }

    @RequestMapping(value = "/organization", params = {"pageNo", "pageSize", "sortBy", "type"}, method = RequestMethod.GET)
    List<OrganizationDto> getSortByType(@RequestParam Integer pageNo,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String sortBy,
                                        @RequestParam TypeOrganization type) {
        return organizationService.getSortedOrganizationByType(pageNo, pageSize, sortBy, type);
    }

    @RequestMapping(value = "/organization", params = {"name"}, method = RequestMethod.GET)
    public List<OrganizationDto> getAll(@RequestParam String name) {
        return organizationService.findAllByName(name);
    }

    @RequestMapping(value = "/organization", params = {"id"}, method = RequestMethod.GET)
    public OrganizationDto getById(@RequestParam Long id) {
        return organizationService.getById(id);
    }

    @RequestMapping(value = "/organization", params = {"type"}, method = RequestMethod.GET)
    public List<OrganizationDto> getByType(@RequestParam TypeOrganization type) {
        return organizationService.getByType(type);
    }

    @GetMapping("/organization")
    public List<OrganizationDto> getAllOrganization() {
        return organizationService.getAll();
    }

    @PostMapping("/organization")
    public void addNewBooking(@RequestBody OrganizationDto organizationDto) {
        organizationService.updateOrganization(organizationDto);
    }
}
