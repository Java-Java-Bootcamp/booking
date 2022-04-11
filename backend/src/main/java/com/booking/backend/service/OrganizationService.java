package com.booking.backend.service;

import com.booking.backend.entity.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> getSortedOrganization(Integer pageNo, Integer pageSize, String sortBy);

}
