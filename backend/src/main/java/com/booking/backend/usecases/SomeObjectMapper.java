package com.booking.backend.usecases;

import com.booking.backend.entities.SomeObject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SomeObjectMapper {

    SomeObject convert(SomeObjectDto someObjectDto);

    SomeObjectDto convert(SomeObject someObject);
}
