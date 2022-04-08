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

        // создаём варианты "кухонь" для заведений
        Cuisine cuisine = new Cuisine();
        cuisine.setName("French");
        cuisine = cuisineRepository.save(cuisine);
        Cuisine cuisine1 = new Cuisine();
        cuisine1.setName("Italian");
        cuisine1 = cuisineRepository.save(cuisine1);

        //создаём юзеров
        User user = new User(1L, "Alex");
        userRepository.save(user);
        User user1 = new User(2L, "Dima");
        userRepository.save(user1);

        // создаём организации
        Organization organization = new Organization(1L, "Restaurant", "10:00-22:00", 10, 3000.5, 8.7, new ArrayList<>(), new ArrayList<>());
        organizationRepository.save(organization);
        Organization organization1 = new Organization(2L, "Bar", "10:00-22:00", 20, 2000.5, 8.0, new ArrayList<>(), new ArrayList<>());
        organizationRepository.save(organization1);

        // создаём букинги
        Booking booking = new Booking(1L, 10, 11, 5, false, user, organization);
        Booking booking1 = new Booking(2L, 11, 12, 5, false, user1, organization1);
        Booking booking2 = new Booking(3L, 12, 13, 5, true, null, organization);
        Booking booking3 = new Booking(4L, 14, 15, 5, true, null, organization1);
        Booking booking4 = new Booking(5L, 15, 16, 5, true, null, organization);
        Booking booking5 = new Booking(6L, 16, 17, 5, true, null, organization);
        Booking booking6 = new Booking(7L, 17, 18, 5, true, null, organization1);
        Booking booking7 = new Booking(8L, 18, 19, 5, true, null, organization1);
        bookingRepository.save(booking);
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
        bookingRepository.save(booking3);
        bookingRepository.save(booking4);
        bookingRepository.save(booking5);
        bookingRepository.save(booking6);
        bookingRepository.save(booking7);


        // создаём слоты для бронирований и кнопок в тг
        Reservation reservation = new Reservation(1L, booking.getBeginning(), booking.getEnding(), 10);
        Reservation reservation1 = new Reservation(2L, booking1.getBeginning(), booking1.getEnding(), 10);
        Reservation reservation2 = new Reservation(3L, booking2.getBeginning(), booking2.getEnding(), 10);
        Reservation reservation3 = new Reservation(4L, booking3.getBeginning(), booking3.getEnding(), 10);
        Reservation reservation4 = new Reservation(5L, booking4.getBeginning(), booking4.getEnding(), 10);
        Reservation reservation5 = new Reservation(6L, booking5.getBeginning(), booking5.getEnding(), 10);
        Reservation reservation6 = new Reservation(7L, booking6.getBeginning(), booking6.getEnding(), 10);
        Reservation reservation7 = new Reservation(8L, booking7.getBeginning(), booking7.getEnding(), 10);

        reservRepo.save(reservation);
        reservRepo.save(reservation1);
        reservRepo.save(reservation2);
        reservRepo.save(reservation3);
        reservRepo.save(reservation4);
        reservRepo.save(reservation5);
        reservRepo.save(reservation6);
        reservRepo.save(reservation7);


        // добавляем организациям слоты на каждый час работы с данными по доступным столикам
        organization1.getReservations().add(reservation);
        organization1.getReservations().add(reservation1);
        organization1.getReservations().add(reservation2);
        organization1.getReservations().add(reservation3);
        organization1.getReservations().add(reservation4);
        organization1.getReservations().add(reservation5);
        organization1.getReservations().add(reservation6);
        organization1.getReservations().add(reservation7);
        organization.getReservations().add(reservation);
        organization.getReservations().add(reservation1);
        organization.getReservations().add(reservation2);
        organization.getReservations().add(reservation3);
        organization.getReservations().add(reservation4);
        organization.getReservations().add(reservation5);
        organization.getReservations().add(reservation6);
        organization.getReservations().add(reservation7);

        // добавляем варианты кухни организации
        organization.getCuisine().add(cuisine);
        organization.getCuisine().add(cuisine1);
        organization.getCuisine().add(cuisine);
        organization.getCuisine().add(cuisine1);
        organizationRepository.save(organization);
        organizationRepository.save(organization1);


    }

    //Заполнить тестовыми данными 20-30 организаций
    //Заполнить тестовыми данными 10 бронирований
}
