package com.booking.backend;


import com.booking.backend.entity.*;
import com.booking.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class DataInitialization implements CommandLineRunner {

    private BookingRepository bookingRepository;
    private OrganizationRepository organizationRepository;
    private UserRepository userRepository;
    private CuisineRepository cuisineRepository;
    private ReservRepo reservRepo;

    @Autowired
    public DataInitialization(BookingRepository bookingRepository, OrganizationRepository organizationRepository,
                              UserRepository userRepository, CuisineRepository cuisineRepository,
                              ReservRepo reservRepo) {
        this.bookingRepository = bookingRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.cuisineRepository = cuisineRepository;
        this.reservRepo = reservRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Cuisine cuisine = Cuisine.builder()
                .name("French")
                .build();
        cuisine = cuisineRepository.save(cuisine);

        Cuisine cuisine1 = Cuisine.builder()
                .name("Italian")
                .build();
        cuisine1 = cuisineRepository.save(cuisine1);


        Organization organization = Organization.builder()
                .name("Restaurant")
                .schedule("10:00-22:00")
                .averageCheck(3000.5)
                .rating(8.7)
                .numbersOfTables(100)
                .reservations(new ArrayList<>())
                .cuisine(new ArrayList<>())
                .build();
        organizationRepository.save(organization);

        Organization organization1 = Organization.builder()
                .name("Bar")
                .schedule("10:00-22:00")
                .averageCheck(2000.5)
                .rating(8.0)
                .numbersOfTables(100)
                .reservations(new ArrayList<>())
                .cuisine(new ArrayList<>())
                .build();
        organizationRepository.save(organization1);

        Reservation reservation1 = Reservation.builder()
                .beginning(10)
                .ending(11)
                .numbersOfTables(10)
                .build();
        Reservation reservation2 = Reservation.builder()
                .beginning(11)
                .ending(12)
                .numbersOfTables(10)
                .build();
        Reservation reservation3 = Reservation.builder()
                .beginning(12)
                .ending(13)
                .numbersOfTables(10)
                .build();
        Reservation reservation4 = Reservation.builder()
                .beginning(13)
                .ending(14)
                .numbersOfTables(10)
                .build();
        Reservation reservation5 = Reservation.builder()
                .beginning(14)
                .ending(15)
                .numbersOfTables(10)
                .build();
        Reservation reservation6 = Reservation.builder()
                .beginning(15)
                .ending(16)
                .numbersOfTables(10)
                .build();
        Reservation reservation7 = Reservation.builder()
                .beginning(16)
                .ending(17)
                .numbersOfTables(10)
                .build();
        Reservation reservation8 = Reservation.builder()
                .beginning(17)
                .ending(18)
                .numbersOfTables(10)
                .build();

        reservRepo.save(reservation1);
        reservRepo.save(reservation2);
        reservRepo.save(reservation3);
        reservRepo.save(reservation4);
        reservRepo.save(reservation5);
        reservRepo.save(reservation6);
        reservRepo.save(reservation7);
        reservRepo.save(reservation8);
        organization1.getReservations().add(reservation1);
        organization1.getReservations().add(reservation2);
        organization1.getReservations().add(reservation3);
        organization1.getReservations().add(reservation4);
        organization1.getReservations().add(reservation5);
        organization1.getReservations().add(reservation6);
        organization1.getReservations().add(reservation7);
        organization1.getReservations().add(reservation8);
        organization1.getCuisine().add(cuisine);
        organization1.getCuisine().add(cuisine1);
        organizationRepository.save(organization1);


        organization.getReservations().add(reservation1);
        organization.getReservations().add(reservation2);
        organization.getReservations().add(reservation3);
        organization.getReservations().add(reservation4);
        organization.getReservations().add(reservation5);
        organization.getReservations().add(reservation6);
        organization.getReservations().add(reservation7);
        organization.getReservations().add(reservation8);
        organization.getCuisine().add(cuisine);
        organization.getCuisine().add(cuisine1);
        organizationRepository.save(organization);


    }

    //Заполнить тестовыми данными 20-30 организаций
    //Заполнить тестовыми данными 10 бронирований
}
