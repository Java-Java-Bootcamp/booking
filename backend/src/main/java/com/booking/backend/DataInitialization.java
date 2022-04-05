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

        User user = new User();
        userRepository.save(user);



        for (int i = 0; i < 30; i++) {
            Organization organization = new Organization();
            organization.setName(String.valueOf(i));
            organization.getCuisine().add(cuisine);
            organization.getCuisine().add(cuisine1);
            organizationRepository.save(organization);
        }

        for (int i = 1; i < 10; i++) {
            Organization organization = organizationRepository.getById((long) i);
            Booking booking = new Booking();
            booking.setNumbersOfGuests(i);
            booking.setUser(user);
            booking.setOrganization(organization);
            bookingRepository.save(booking);
        }

    }

    //Заполнить тестовыми данными 20-30 организаций
    //Заполнить тестовыми данными 10 бронирований
}
