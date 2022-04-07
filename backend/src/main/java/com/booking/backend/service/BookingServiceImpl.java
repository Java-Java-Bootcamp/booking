package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        return bookingRepository.getAllByBookedTrueAndOrganizationName(nameOfOrganization);
    }

    @Override
    public List<BookingDto> getAllBookings(Integer pageNo, Integer pageSize, String sortBy) {
        if (sortBy.equals("rate")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("organization.rating"));
            Page<Booking> pagedResult = bookingRepository.findAll(paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent().stream()
                        .map(e -> new BookingDto(e.getId(),
                                e.getOrganization().getName(),
                                e.getOrganization().getRating(),
                                e.getOrganization().getAverageCheck(),
                                e.getOrganization().getCuisine(),
                                e.getOrganization().getSchedule())).toList();
            } else {
                return new ArrayList<BookingDto>();
            }
        }
        if (sortBy.equals("bill")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("organization.averageCheck"));
            Page<Booking> pagedResult = bookingRepository.findAll(paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent().stream()
                        .map(e -> new BookingDto(e.getId(),
                                e.getOrganization().getName(),
                                e.getOrganization().getRating(),
                                e.getOrganization().getAverageCheck(),
                                e.getOrganization().getCuisine(),
                                e.getOrganization().getSchedule())).toList();
            } else {
                return new ArrayList<BookingDto>();
            }
        }
        if (sortBy.equals("time")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("dateOfBeginning"));
            Page<Booking> pagedResult = bookingRepository.findAll(paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent().stream()
                        .map(e -> new BookingDto(e.getId(),
                                e.getOrganization().getName(),
                                e.getOrganization().getRating(),
                                e.getOrganization().getAverageCheck(),
                                e.getOrganization().getCuisine(),
                                e.getOrganization().getSchedule())).toList();
            } else {
                return new ArrayList<BookingDto>();
            }
        }
        return null;
    }
}
