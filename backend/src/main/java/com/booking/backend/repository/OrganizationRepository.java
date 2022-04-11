package com.booking.backend.repository;

import com.booking.backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> getAllByName(String name);
}
