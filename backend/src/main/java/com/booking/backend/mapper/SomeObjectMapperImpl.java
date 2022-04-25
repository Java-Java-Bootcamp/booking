package com.booking.backend.mapper;

import com.booking.backend.dto.SomeObjectDto;
import com.booking.backend.entity.SomeObject;
import org.springframework.stereotype.Component;

@Component
public class SomeObjectMapperImpl implements SomeObjectMapper {

    private OrganizationMapper organizationMapper;

    public SomeObjectMapperImpl(OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    @Override
    public SomeObject convertFromSomeObjectDtoToSomeObject(SomeObjectDto someObjectDto) {
        return new SomeObject(someObjectDto.id(), someObjectDto.description(),
                organizationMapper.convertFromOrganizationDtoToOrganization(someObjectDto.organizationDto()));
    }

    @Override
    public SomeObjectDto convertFromSomeObjectToSomeObjectDto(SomeObject someObject) {
        return new SomeObjectDto(someObject.getId(), someObject.getDescription(),
                organizationMapper.convertFromOrganizationToOrganizationDto(someObject.getOrganization()));
    }
}
