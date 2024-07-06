package com.ticket.bookingsystem.movies.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.bookingsystem.movies.databasefiles.Seat;
import com.ticket.bookingsystem.movies.service.BookingService;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("shows/seats/{showId}")
    public List<Seat> getSeatsForShow(@PathVariable int showId) {
        bookingService.setShowIdToSeat(showId);
        return bookingService.getSeatForShow(showId);
    }

}
