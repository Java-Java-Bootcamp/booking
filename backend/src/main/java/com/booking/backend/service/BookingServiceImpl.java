package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Organization;
import com.booking.backend.entity.User;
import com.booking.backend.repository.BookingRepository;
import com.booking.backend.repository.OrganizationRepository;
import com.booking.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, OrganizationRepository organizationRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

//    @Override
//    public List<Booking> findAllByBookedFalseAndOrganizationName(String nameOfOrganization) {
//        return bookingRepository.getAllByBookedTrueAndOrganizationName(nameOfOrganization);
//    }

    @Override
    public List<BookingDto> getAllBookings(Integer pageNo, Integer pageSize, String sortBy) {
//        if (sortBy.equals("rate")) {
//            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("organization.rating"));
//            Page<Booking> pagedResult = bookingRepository.findAll(paging);
//            if (pagedResult.hasContent()) {
//                return pagedResult.getContent().stream()
//                        .map(e -> new BookingDto(e.getId(),
//                                e.getOrganization().getName(),
//                                e.getOrganization().getRating(),
//                                e.getOrganization().getAverageCheck(),
//                                e.getOrganization().getCuisine(),
//                                e.getOrganization().getReservations()
//                                        .stream()
//                                        .filter(r -> r.getNumbersOfTables() > 0)
//                                        .collect(Collectors.toList())))
//                        .collect(Collectors.toList());
//            } else {
//                return new ArrayList<>();
//            }
//        }
//        if (sortBy.equals("bill")) {
//            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("organization.averageCheck"));
//            Page<Booking> pagedResult = bookingRepository.findAll(paging);
//            if (pagedResult.hasContent()) {
//                return pagedResult.getContent().stream()
//                        .map(e -> new BookingDto(e.getOrganization().getReservations().stream().ma,
//                                e.getUser(),
//                                e.getOrganization().getId()))
//                        .collect(Collectors.toList());
//            } else {
//                return new ArrayList<>();
//            }
//        }
//        if (sortBy.equals("time")) {
//            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("dateOfBeginning"));
//            Page<Booking> pagedResult = bookingRepository.findAll(paging);
//            if (pagedResult.hasContent()) {
//                return pagedResult.getContent().stream()
//                        .map(e -> new BookingDto(e.getId(),
//                                e.getOrganization().getName(),
//                                e.getOrganization().getRating(),
//                                e.getOrganization().getAverageCheck(),
//                                e.getOrganization().getCuisine(),
//                                e.getOrganization().getReservations()
//                                        .stream()
//                                        .filter(r -> r.getNumbersOfTables() > 0)
//                                        .collect(Collectors.toList())))
//                        .collect(Collectors.toList());
//            } else {
//                return new ArrayList<>();
//            }
//        }
        return null;
    }

    @Override
    public void addNewBooking(BookingDto bookingDto) {
        Organization organization = organizationRepository.getById(bookingDto.organizationId());
        System.out.println("orga" + organization);
//        User user = userRepository.getById(bookingDto.userDto().id());
//        System.out.println("user" + user);
        System.out.println("Done service");
//        if (user == null) {
//            user = User.builder()
//                    .name("Default")
//                    .build();
//        }
        User user = User.builder().build();
        user = userRepository.save(user);
        Booking booking = Booking.builder()
                .beginning(bookingDto.reservation().getBeginning())
                .ending(bookingDto.reservation().getEnding())
                .organization(organization)
                .user(user)
                .build();
        bookingRepository.save(booking);
    }
}
