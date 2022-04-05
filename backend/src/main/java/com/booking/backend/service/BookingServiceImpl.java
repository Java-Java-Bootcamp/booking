package com.booking.backend.service;

import com.booking.backend.entity.Booking;
import com.booking.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllByOrganizationName(String nameOfOrganization) {
        return bookingRepository.getAllByOrganizationName(nameOfOrganization);
    }

//    @Override
//    public List<Booking> getAllBookings(String nameOfOrganization) {
//        List<Booking> list = bookingRepository.findAll();
//
//    }
}
