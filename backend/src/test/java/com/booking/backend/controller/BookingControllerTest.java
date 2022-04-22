package com.booking.backend.controller;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Person;
import com.booking.backend.mapper.BookingMapper;
import com.booking.backend.mapper.PersonMapper;
import com.booking.backend.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Test
    void addNewBooking() throws Exception {
        Person person = personRepository.save(new Person(1L, "Alex"));
        BookingDto bookingDto = new BookingDto(1L, personMapper.convert(person));

        mockMvc.perform(post("/bookings").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}