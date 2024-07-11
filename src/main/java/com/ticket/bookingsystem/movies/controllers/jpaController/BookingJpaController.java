package com.ticket.bookingsystem.movies.controllers.jpaController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ticket.bookingsystem.movies.databasefiles.Seat;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.ShowRepo;
import com.ticket.bookingsystem.movies.exceptions.SeatAlreadyBookedException;
import com.ticket.bookingsystem.movies.exceptions.SeatNotFoundException;
import com.ticket.bookingsystem.movies.exceptions.ShowNotFoundException;
import com.ticket.bookingsystem.movies.exceptions.UserNotFoundException;
import com.ticket.bookingsystem.movies.controllers.jpaController.jpaDaoService.BookingJpaService;

@RestController
public class BookingJpaController {

    @Autowired
    private ShowRepo showRepoService;

    @Autowired
    private BookingJpaService bookingJpaService;

    @GetMapping("shows/seats/{showId}")
    public List<Seat> getSeatsForShow(@PathVariable int showId) {

        try {
            showRepoService.findById(showId);
            return bookingJpaService.getSeatForShow(showId);
        } catch (ShowNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path = "{userId}/shows/seats/{showId}/{seatNumber}")
    public Seat bookSeat(@PathVariable int userId, @PathVariable int showId, @PathVariable int seatNumber) {

        try {
            return bookingJpaService.bookSeat(showId, seatNumber, userId);
        } catch (ShowNotFoundException | SeatNotFoundException | SeatAlreadyBookedException | UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
