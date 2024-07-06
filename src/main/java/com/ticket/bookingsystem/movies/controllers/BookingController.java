package com.ticket.bookingsystem.movies.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping(path = "{userId}/shows/seats/{showId}/{seatNumber}")
    public Seat bookSeat(@PathVariable int userId, @PathVariable int showId, @PathVariable int seatNumber) {
       return  bookingService.bookSeat(showId, seatNumber, userId);

        // try {
        // return showService.bookSeat(showId, seatNumber, userId);
        // } catch (ShowNotFoundException | SeatNotFoundException |
        // SeatAlreadyBookedException e) {
        // throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        // }

    }

}
