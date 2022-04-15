//package com.booking.backend;
//
//
//import com.booking.backend.entity.*;
//import com.booking.backend.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class DataInitialization implements CommandLineRunner {
//
//    private BookingRepository bookingRepository;
//    private OrganizationRepository organizationRepository;
//    private PersonRepository personRepository;
//
//    @Autowired
//    public DataInitialization(BookingRepository bookingRepository, OrganizationRepository organizationRepository,
//                              PersonRepository personRepositoryo) {
//        this.bookingRepository = bookingRepository;
//        this.organizationRepository = organizationRepository;
//        this.personRepository = personRepository;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//
////        Organization organization = Organization.builder()
////                .name("Restaurant")
////                .schedule("10:00-22:00")
////                .averageCheck(3000.5)
////                .rating(8.7)
////                .build();
////        organizationRepository.save(organization);
////
////        Organization organization1 = Organization.builder()
////                .name("Bar")
////                .schedule("10:00-22:00")
////                .averageCheck(2000.5)
////                .rating(8.0)
////                .build();
////        organizationRepository.save(organization1);
////
//////        Reservation reservation1 = Reservation.builder()
//////                .beginning(10)
//////                .ending(11)
//////                .build();
//////        Reservation reservation2 = Reservation.builder()
//////                .beginning(11)
//////                .ending(12)
//////                .build();
//////        Reservation reservation3 = Reservation.builder()
//////                .beginning(12)
//////                .ending(13)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation4 = Reservation.builder()
//////                .beginning(13)
//////                .ending(14)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation5 = Reservation.builder()
//////                .beginning(14)
//////                .ending(15)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation6 = Reservation.builder()
//////                .beginning(15)
//////                .ending(16)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation7 = Reservation.builder()
//////                .beginning(16)
//////                .ending(17)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation8 = Reservation.builder()
//////                .beginning(17)
//////                .ending(18)
//////                .numbersOfTables(10)
//////                .build();
//////
//////        Reservation reservation9 = Reservation.builder()
//////                .beginning(10)
//////                .ending(11)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation10 = Reservation.builder()
//////                .beginning(11)
//////                .ending(12)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation11 = Reservation.builder()
//////                .beginning(12)
//////                .ending(13)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation12 = Reservation.builder()
//////                .beginning(13)
//////                .ending(14)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation13 = Reservation.builder()
//////                .beginning(14)
//////                .ending(15)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation14 = Reservation.builder()
//////                .beginning(15)
//////                .ending(16)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation15 = Reservation.builder()
//////                .beginning(16)
//////                .ending(17)
//////                .numbersOfTables(10)
//////                .build();
//////        Reservation reservation16 = Reservation.builder()
//////                .beginning(17)
//////                .ending(18)
//////                .numbersOfTables(10)
//////                .build();
//////
//////        reservRepo.save(reservation1);
//////        reservRepo.save(reservation2);
//////        reservRepo.save(reservation3);
//////        reservRepo.save(reservation4);
//////        reservRepo.save(reservation5);
//////        reservRepo.save(reservation6);
//////        reservRepo.save(reservation7);
//////        reservRepo.save(reservation8);
//////        reservRepo.save(reservation9);
//////        reservRepo.save(reservation10);
//////        reservRepo.save(reservation11);
//////        reservRepo.save(reservation12);
//////        reservRepo.save(reservation13);
//////        reservRepo.save(reservation14);
//////        reservRepo.save(reservation15);
//////        reservRepo.save(reservation16);
//////        organization1.getReservations().add(reservation1);
//////        organization1.getReservations().add(reservation2);
//////        organization1.getReservations().add(reservation3);
//////        organization1.getReservations().add(reservation4);
//////        organization1.getReservations().add(reservation5);
//////        organization1.getReservations().add(reservation6);
//////        organization1.getReservations().add(reservation7);
//////        organization1.getReservations().add(reservation8);
////        organizationRepository.save(organization1);
////
////
//////        organization.getReservations().add(reservation9);
//////        organization.getReservations().add(reservation10);
//////        organization.getReservations().add(reservation11);
//////        organization.getReservations().add(reservation12);
//////        organization.getReservations().add(reservation13);
//////        organization.getReservations().add(reservation14);
//////        organization.getReservations().add(reservation15);
//////        organization.getReservations().add(reservation16);
////        organizationRepository.save(organization);
////
////
////    }
//
//    //Заполнить тестовыми данными 20-30 организаций
//    //Заполнить тестовыми данными 10 бронирований
//}
