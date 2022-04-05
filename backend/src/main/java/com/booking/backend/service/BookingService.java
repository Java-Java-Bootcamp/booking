package com.booking.backend.service;

import com.booking.backend.entity.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> findAllByBookedFalseAndOrganizationName(String nameOfOrganization);
}
