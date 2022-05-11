package com.booking.backend.mapper;

import com.booking.backend.dto.SomeObjectDto;
import com.booking.backend.entity.SomeObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
public class SomeObjectMapperImpl implements SomeObjectMapper {

    private final OrganizationMapper organizationMapper;

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
