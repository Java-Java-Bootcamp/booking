package com.booking.backend;


import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Cuisine;
import com.booking.backend.entity.Organization;
import com.booking.backend.entity.User;
import com.booking.backend.repository.BookingRepository;
import com.booking.backend.repository.CuisineRepository;
import com.booking.backend.repository.OrganizationRepository;
import com.booking.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInitialization implements CommandLineRunner {

    private BookingRepository bookingRepository;
    private OrganizationRepository organizationRepository;
    private UserRepository userRepository;
    private CuisineRepository cuisineRepository;

    @Autowired
    public DataInitialization(BookingRepository bookingRepository, OrganizationRepository organizationRepository,
                              UserRepository userRepository, CuisineRepository cuisineRepository) {
        this.bookingRepository = bookingRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.cuisineRepository = cuisineRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Cuisine cuisine = new Cuisine();
        cuisine.setName("French");
        cuisine = cuisineRepository.save(cuisine);
        Cuisine cuisine1 = new Cuisine();
        cuisine1.setName("Italian");
        cuisine1 = cuisineRepository.save(cuisine1);

        User user = new User(1L, "Alex");
        userRepository.save(user);
        User user1 = new User(2L, "Dima");
        userRepository.save(user1);

        Organization organization = new Organization(1L, "Restaurant", "10:00-22:00", 10, 3000.5, 8.7, new ArrayList<>());
        organization.getCuisine().add(cuisine);
        organization.getCuisine().add(cuisine1);
        organizationRepository.save(organization);

        Organization organization1 = new Organization(2L, "Bar", "10:00-22:00", 20, 2000.5, 8.0, new ArrayList<>());
        organization.getCuisine().add(cuisine);
        organization.getCuisine().add(cuisine1);
        organizationRepository.save(organization1);

        Organization organization2 = new Organization(3L, "Cafe", "10:00-22:00", 20, 2000.5, 5.0, new ArrayList<>());
        organization.getCuisine().add(cuisine);
        organization.getCuisine().add(cuisine1);
        organizationRepository.save(organization2);

        Booking booking = new Booking(1L, LocalDateTime.now(), LocalDateTime.now(), 5, false, user, organization);
        Booking booking1 = new Booking(2L, LocalDateTime.now(), LocalDateTime.now(), 5, false, user1, organization1);
        Booking booking2 = new Booking(3L, LocalDateTime.now(), LocalDateTime.now(), 5, true, null, organization);
        Booking booking3 = new Booking(4L, LocalDateTime.now(), LocalDateTime.now(), 5, true, null, organization1);
        Booking booking4 = new Booking(5L, LocalDateTime.now(), LocalDateTime.now(), 5, true, null, organization1);
        Booking booking5 = new Booking(6L, LocalDateTime.now(), LocalDateTime.now(), 5, true, null, organization1);
        Booking booking6 = new Booking(7L, LocalDateTime.now(), LocalDateTime.now(), 5, true, null, organization1);
        Booking booking7 = new Booking(8L, LocalDateTime.now(), LocalDateTime.now(), 5, true, null, organization1);
        bookingRepository.save(booking);
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
        bookingRepository.save(booking3);
        bookingRepository.save(booking4);
        bookingRepository.save(booking5);
        bookingRepository.save(booking6);
        bookingRepository.save(booking7);

    }

    //Заполнить тестовыми данными 20-30 организаций
    //Заполнить тестовыми данными 10 бронирований
}
