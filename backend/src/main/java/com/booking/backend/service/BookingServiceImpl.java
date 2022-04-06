package com.booking.backend.service;

import com.booking.backend.entity.Booking;
import com.booking.backend.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public List<Booking> findAllByBookedFalseAndOrganizationName(String nameOfOrganization) {
        return bookingRepository.getAllByBookedFalseAndOrganizationName(nameOfOrganization);
    }

    @Override
    public List<Booking> getAllBookings(Integer pageNo, Integer pageSize) {
        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize);
        Page<Booking> pagedResult = bookingRepository.findAll((org.springframework.data.domain.Pageable) paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Booking>();
        }
    }

}
